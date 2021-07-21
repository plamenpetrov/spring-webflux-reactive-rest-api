package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegionRestaurantDTO {

    private Integer id;
    private String name;
    private String restaurantName;
}
