package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.dto.RegionRestaurantDTO;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Restaurant;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository.RegionRepository;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private List<Region> dummyRegions = new ArrayList<>();

    public RegionService() {
        dummyRegions.add(new Region(1, "Region one", "bg"));
        dummyRegions.add(new Region(2, "Region two", "bg"));
        dummyRegions.add(new Region(3, "Region three", "bg"));
        dummyRegions.add(new Region(4, "Region four", "bg"));
        dummyRegions.add(new Region(5, "Region five", "bg"));
    }

    public Flux<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Mono<Region> findById(Integer id) {
        return regionRepository.findById(id);
    }

    public Flux<RegionRestaurantDTO> getRegionAndRestaurants(Integer regionId) {
        Mono<Region> region = regionRepository.findById(regionId)
                .subscribeOn(Schedulers.parallel());

        Flux<Restaurant> restaurants = restaurantRepository.getRestaurantsByRegionId(regionId)
                .subscribeOn(Schedulers.parallel());

        return restaurants.flatMap(fluxItem ->
                region.map(monoItem -> new RegionRestaurantDTO(monoItem.getId(), monoItem.getName(), fluxItem.getName())));
    }

    public Mono<Region> create(Region region) { return this.regionRepository.save(region); }

    public Mono<Region> update(Integer regionId, Region region) {
        return regionRepository.findById(regionId)
                .flatMap(dbRegion -> {
                    dbRegion.setName(region.getName());
                    dbRegion.setCountry_code(region.getCountry_code());
                    return regionRepository.save(dbRegion);
                });
    }

    public Mono<Region> delete(Integer regionId) {
        return regionRepository.findById(regionId)
                .flatMap(existingRegion -> regionRepository.delete(existingRegion)
                .then(Mono.just(existingRegion)));
    }

    public Flux<Region> getAllRegionsStream(int durationInterval) {
        Flux<Region> regions = Flux.generate(() -> 0,
                (index, sink) -> {
                    Region randomRegion = randomize(dummyRegions.get(index));
                    sink.next(randomRegion);
                    System.out.println("Generated new Region");
                    return (++index) % dummyRegions.size();
                });

        if(durationInterval > 0) {
            return regions.delayElements(Duration.ofSeconds(durationInterval));
        }

        return regions;
    }

    private Region randomize(Region initial) {
        return new Region(
                initial.getId(),
                initial.getName(),
                initial.getCountry_code()
        );
    }
}
