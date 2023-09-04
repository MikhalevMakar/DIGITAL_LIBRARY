package ru.nsu.ccfit.mikhalev.digital_library.configuration.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomConfigurer<B extends HttpSecurityBuilder<B>,
                              T extends CustomConfigurer<B, T>> extends AbstractHttpConfigurer<T, B> {

    private CustomSecurityFilter customSecurityFilter;

    @Override
    public void init(B builder) throws Exception {
        super.init(builder);
        customSecurityFilter =  new CustomSecurityFilter();
    }

    @Override
    public void configure(B builder) throws Exception {
        super.configure(builder);
    }
}
