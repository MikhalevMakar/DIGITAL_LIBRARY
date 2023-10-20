package ru.nsu.ccfit.mikhalev.digital_library.configuration.security;

import io.swagger.v3.oas.models.PathItem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class CustomConfigurer<B extends HttpSecurityBuilder<B>,
                              T extends CustomConfigurer<B, T>> extends AbstractHttpConfigurer<T, B> {

    private CustomSecurityFilter customSecurityFilter;

    @Override
    public void init(B builder) throws Exception {
        super.init(builder);
        customSecurityFilter = new CustomSecurityFilter();
    }

    @Override
    public void configure(B builder) throws Exception {
        super.configure(builder);
        ApplicationContext applicationContext = builder.getSharedObject(ApplicationContext.class);
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(applicationContext.getBean(PasswordEncoder.class));
        authenticationProvider.setUserDetailsService(applicationContext.getBean(UserDetailsService.class));
        AuthenticationManagerBuilder authenticationManagerBuilder = builder.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        customSecurityFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        customSecurityFilter.setSecurityContextRepository(builder.getSharedObject(SecurityContextRepository.class));
        customSecurityFilter.setRequestMatcher(new AntPathRequestMatcher("/login", PathItem.HttpMethod.POST.name()));

        builder.addFilterBefore(customSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}