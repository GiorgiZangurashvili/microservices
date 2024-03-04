package com.zangura.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDER_LINE_ITEMS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_LINE_ITEMS_ID_GEN")
    @SequenceGenerator(name = "ORDER_LINE_ITEMS_ID_GEN", sequenceName = "ORDER_LINE_ITEMS_ID_SEQ",
            allocationSize = 1)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
