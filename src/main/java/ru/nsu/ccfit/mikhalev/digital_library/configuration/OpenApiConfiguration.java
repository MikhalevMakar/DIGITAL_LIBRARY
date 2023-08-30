package ru.nsu.ccfit.mikhalev.digital_library.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springdoc.webmvc.ui.SwaggerWelcomeCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class OpenApiConfiguration {
//    private final PropertyResolverUtils propertyResolverUtils;
//
//    @Autowired
//    public OpenApiConfiguration(PropertyResolverUtils propertyResolverUtils) {
//        this.propertyResolverUtils = propertyResolverUtils;
//    }

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//            .info(new Info().title(message("api.title"))
//                .description(message("api.description"))
//                .version("v1.0.0")
//                .license(new License().name(message("api.license")).url("https://mikhalev.ru")));
//    }
//
//    @Bean
//    public SwaggerWelcomeCommon swaggerWelcomeCommon() {
//        return new SwaggerWelcomeCommon () {
//            @Override
//            protected String buildUrlWithContextPath(String swaggerUiUrl){
//                return null;
//            }
//
//            @Override
//            protected void calculateUiRootPath(StringBuilder... sbUrls){
//
//            }
//
//            @Override
//            protected String buildApiDocUrl(){
//                return null;
//            }
//
//            @Override
//            protected String buildSwaggerConfigUrl(){
//                return null;
//            }
//        };
//     }

//    private String message(String property) {
//        return this.propertyResolverUtils.resolve(property, Locale.getDefault());
//    }
}
