package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Restaurant;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RestaurantRepository extends ReactiveCrudRepository<Restaurant, Integer> {
    @Query("select id, region_id, name from restaurants where region_id = :regionId")
    Flux<Restaurant> getRestaurantsByRegionId(int regionId);
}
