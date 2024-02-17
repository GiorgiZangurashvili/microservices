package com.zangura.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "price")
    private BigDecimal price;
}
