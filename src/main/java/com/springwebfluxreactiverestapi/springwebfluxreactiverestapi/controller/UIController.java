package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.controller;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin(value = { "http://localhost:8001" },
        maxAge = 900
)
@RestController
public class UIController {

    @Autowired
    private RegionService regionService;

    @GetMapping(path = "/regions/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Region> feed() {
        return this.regionService.getAllRegionsStream(1);
    }
}