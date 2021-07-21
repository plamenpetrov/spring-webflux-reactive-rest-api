package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.controller;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Restaurant;
import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public Flux<Restaurant> getRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{restId}")
    public Mono<ResponseEntity<Restaurant>> getRestaurantById(@PathVariable Integer restId) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("Get data for restaurant with ID: " + restId);
        Mono<Restaurant> restaurant = restaurantService.findById(restId);
        return restaurant.map( r -> ResponseEntity.ok(r))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Restaurant> createRestaurant(@RequestBody Restaurant restaurant){
        return restaurantService.create(restaurant);
    }

    @PutMapping("/{restId}")
    public Mono<ResponseEntity<Restaurant>> updateRestaurant(@PathVariable Integer restId, @RequestBody Restaurant restaurant){
        return restaurantService.update(restId, restaurant)
                .map(updatedRestaurant -> ResponseEntity.ok(updatedRestaurant))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{restId}")
    public Mono<ResponseEntity<Void>> deleteRestaurant(@PathVariable Integer restId){
        return restaurantService.delete(restId)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
