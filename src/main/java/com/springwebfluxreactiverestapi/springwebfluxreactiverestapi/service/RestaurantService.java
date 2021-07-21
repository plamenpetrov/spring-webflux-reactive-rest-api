package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Restaurant;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Flux<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Flux<Restaurant> getRestaurantsByRegionId(Integer regionId) {
        return restaurantRepository.getRestaurantsByRegionId(regionId);
    }

    public Mono<Restaurant> findById(Integer id) {
        return restaurantRepository.findById(id);
    }

    public Mono<Restaurant> create(Restaurant restaurant) { return this.restaurantRepository.save(restaurant); }

    public Mono<Restaurant> update(Integer restId, Restaurant restaurant) {
        return restaurantRepository.findById(restId)
                .flatMap((dbRegion -> {
                    dbRegion.setName(restaurant.getName());
                    dbRegion.setRegion_id(restaurant.getRegion_id());
                    return restaurantRepository.save(dbRegion);
                }));
    }

    public Mono<Restaurant> delete(Integer restId) {
        return restaurantRepository.findById(restId)
                .flatMap(existingRestaurant -> restaurantRepository.delete(existingRestaurant)
                        .then(Mono.just(existingRestaurant)));
    }
}
