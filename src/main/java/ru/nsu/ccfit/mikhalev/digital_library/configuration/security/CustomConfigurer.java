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
        log.info("32");
        ApplicationContext applicationContext = builder.getSharedObject(ApplicationContext.class);
        CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
        log.info("35");
        authenticationProvider.setPasswordEncoder(applicationContext.getBean(PasswordEncoder.class));
        authenticationProvider.setUserDetailsService(applicationContext.getBean(UserDetailsService.class));
        log.info("38");
        AuthenticationManagerBuilder authenticationManagerBuilder = builder.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        log.info("41");
        customSecurityFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        customSecurityFilter.setSecurityContextRepository(builder.getSharedObject(SecurityContextRepository.class));
        customSecurityFilter.setRequestMatcher(new AntPathRequestMatcher("/login", PathItem.HttpMethod.POST.name()));
        log.info("45");
        builder.addFilterBefore(customSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }
}