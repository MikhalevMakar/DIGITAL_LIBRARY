package ru.nsu.ccfit.mikhalev.digital_library.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

public class CustomSecurityFilter extends OncePerRequestFilter {
    private final SecurityContextHolderStrategy secContHolderStrategy
                                    = SecurityContextHolder.getContextHolderStrategy();

    private final ObjectMapper objectMapper = new ObjectMapper();

    private RequestMatcher requestMatcher;

    private AuthenticationManager authenticationManager;

    private SecurityContextRepository securityContextRepository;

    private final AuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler("auth/success");

    private final AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler("auth/failed");

    public CustomSecurityFilter(){
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        if(requestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = obtainBody(request);
            authentication =  authenticationManager.authenticate(authentication);
            successfulAuthenticationRequest(request, response, authentication);
        } catch(AuthenticationException exception) {
            unSuccessfulAuthenticationRequest(request, response, exception);
        }
    }

    protected void successfulAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SecurityContext context = secContHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        secContHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, request, response);
        successHandler.onAuthenticationSuccess(request, response, authentication);
    }

    protected void unSuccessfulAuthenticationRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationEx) throws ServletException, IOException {
        SecurityContext context = secContHolderStrategy.getContext();
        secContHolderStrategy.clearContext();
        context.setAuthentication(null);
        failureHandler.onAuthenticationFailure(request, response, authenticationEx);

    }
    protected Authentication obtainBody(HttpServletRequest request) throws IOException {
        Map<String, String> map = objectMapper.readValue(request.getInputStream (), Map.class);
        String username = map.get ("username");
        String password = map.get ("password");

        if (!StringUtils.hasLength (username) || StringUtils.hasLength (password)) {
            throw new BadCredentialsException("api.digital-library.custom-security-filter.response.empty-password-or-username");
        }
        return UsernamePasswordAuthenticationToken.unauthenticated (username, password);
    }
}
