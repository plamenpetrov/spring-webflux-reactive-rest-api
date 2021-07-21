package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class ApplicationRouter {

    @Bean
    public RouterFunction<ServerResponse> route(RegionHandler regionHandler) {
        return RouterFunctions
                .route(GET("/regions").and(accept(MediaType.APPLICATION_JSON)), regionHandler::getRegions)
                .andRoute(GET("/regions-stream").and(accept(MediaType.APPLICATION_STREAM_JSON)), regionHandler::streamGetRegions);
    }
}
