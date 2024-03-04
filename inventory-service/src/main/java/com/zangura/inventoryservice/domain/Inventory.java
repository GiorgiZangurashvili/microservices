package com.zangura.inventoryservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVENTORY_ID_GEN")
    @SequenceGenerator(name = "INVENTORY_ID_GEN", sequenceName = "INVENTORY_ID_SEQ", allocationSize = 1)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
