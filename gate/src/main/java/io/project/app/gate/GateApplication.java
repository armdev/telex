package io.project.app.gate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Slf4j
@ComponentScan
public class GateApplication {
    
    ///http://localhost:2024/telex/api/send?telex=4545
    
    // HA PROXY http://localhost:8405/telex/api/send?telex=85

    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(GateApplication.class);
        application.setBannerMode(Banner.Mode.CONSOLE);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(args);
    }

    @Bean
    @CrossOrigin
    public RouteLocator telex(RouteLocatorBuilder builder) {
        log.info("Gate to Telex");
        return builder.routes()
                .route("um_route", r -> r
                .path("/telex/**")                 
                .filters(f -> f.stripPrefix(1).retry(5)
                .addResponseHeader("Access-Control-Expose-Headers", "scope, client,Origin,Accept-Language,Accept-Encoding")
                )
                .uri("http://telex:2025/")
                //.uri("lb://telex/")
                )
                .build();
    }

}
