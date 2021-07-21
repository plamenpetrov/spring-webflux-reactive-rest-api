package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.handler;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service.RegionService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.*;

@Component
public class RegionHandler {

    private RegionService regionService;

    public RegionHandler(RegionService regionService) {
        this.regionService = regionService;
    }

    public Mono<ServerResponse> getRegions(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(this.regionService.getAllRegionsStream(0).take(4), Region.class);
    }

    public Mono<ServerResponse> streamGetRegions(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_STREAM_JSON)
                .body(this.regionService.getAllRegionsStream(1), Region.class);
    }
}
