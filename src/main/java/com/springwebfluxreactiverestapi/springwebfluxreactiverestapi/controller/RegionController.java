package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.controller;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.dto.RegionRestaurantDTO;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public Flux<Region> getRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{regionId}")
    public Mono<ResponseEntity<Region>> getRegionById(@PathVariable Integer regionId) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Get data for region with ID: " + regionId);
        Mono<Region> region = regionService.findById(regionId);
        return region.map( r -> ResponseEntity.ok(r))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{regionId}/restaurants")
    public Flux<RegionRestaurantDTO> getRegionAndRestaurants(@PathVariable Integer regionId){
        return regionService.getRegionAndRestaurants(regionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Region> createRegion(@RequestBody Region region){
        return regionService.create(region);
    }

    @PutMapping("/{regionId}")
    public Mono<ResponseEntity<Region>> updateRegion(@PathVariable Integer regionId, @RequestBody Region region){
        return regionService.update(regionId,region)
                .map(updatedRegion -> ResponseEntity.ok(updatedRegion))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{regionId}")
    public Mono<ResponseEntity<Void>> deleteRegion(@PathVariable Integer regionId){
        return regionService.delete(regionId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
