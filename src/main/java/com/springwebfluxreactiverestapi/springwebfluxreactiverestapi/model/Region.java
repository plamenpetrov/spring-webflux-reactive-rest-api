package com.springwebfluxreactiverestapi.springwebfluxreactiverestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("regions")
public class Region {

    @Id
    private Integer id;
    private String name;
    private String country_code;
}
