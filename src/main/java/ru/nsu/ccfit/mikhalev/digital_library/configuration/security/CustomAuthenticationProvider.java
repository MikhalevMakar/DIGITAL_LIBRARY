package ru.nsu.ccfit.mikhalev.digital_library.configuration.security;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Data
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        } catch(UsernameNotFoundException exception) {
            log.warn("User not found by name " + authentication.getName());
            throw new UsernameNotFoundException(authentication.getName());
        }

        if(passwordEncoder.matches(authentication.getCredentials().toString(),
                                   userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        UsernamePasswordAuthenticationToken result =  UsernamePasswordAuthenticationToken.authenticated(userDetails,
                                                                                                        authentication.getCredentials(),
                                                                                                        authentication.getAuthorities()
        );

        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return false;
    }
}
