package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.initalize;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Restaurant;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository.RegionRepository;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class RegionsInitializer implements CommandLineRunner {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void run(String... args) {
        initialDataSetup();
    }

    private List<Region> getRegionData(){
        return Arrays.asList(new Region(null,"Sofia", "bg"),
                new Region(null,"Plovdiv", "bg"),
                new Region(null,"Varna", "bg"),
                new Region(null,"Burgas", "bg")
        );
    }

    private List<Restaurant> getRestaurantData(){
        return Arrays.asList(new Restaurant(null, 1, "Pizza restaurant"),
                new Restaurant(null, 1, "Pizza restaurant two"),

                new Restaurant(null, 2, "Sushi restaurant"),
                new Restaurant(null, 2, "Sushi restaurant two"),

                new Restaurant(null, 3, "Chinese restaurant"),
                new Restaurant(null, 3, "Chinese restaurant two"),
                new Restaurant(null, 3, "Chinese restaurant three"),

                new Restaurant(null, 4, "Italian restaurant"),
                new Restaurant(null, 4, "Italian restaurant two"),
                new Restaurant(null, 4, "Italian restaurant three")
        );
    }

    private void initialDataSetup() {
        regionRepository.deleteAll()
                .thenMany(Flux.fromIterable(getRegionData()))
                .flatMap(regionRepository::save)
                .thenMany(regionRepository.findAll())
                .subscribe(region -> {
                    log.info("Regions Inserted from CommandLineRunner " + region);
                });

        restaurantRepository.deleteAll()
                .thenMany(Flux.fromIterable(getRestaurantData()))
                .flatMap(restaurantRepository::save)
                .thenMany(restaurantRepository.findAll())
                .subscribe(restaurant -> {
                    log.info("Restaurants Inserted from CommandLineRunner " + restaurant);
                });

    }

}
