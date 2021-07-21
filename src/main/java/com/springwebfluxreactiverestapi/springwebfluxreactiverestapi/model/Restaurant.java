package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("restaurants")
public class Restaurant {

    @Id
    private Integer id;
    private Integer region_id;
    private String name;
}
