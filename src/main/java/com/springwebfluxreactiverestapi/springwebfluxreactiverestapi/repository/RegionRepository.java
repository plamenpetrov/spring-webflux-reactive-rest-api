package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.repository;

import com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model.Region;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RegionRepository extends ReactiveCrudRepository<Region, Integer> {
}
