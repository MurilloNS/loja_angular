package com.murillons.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoUpdate {
    private String name;
    private String brand;
    private String description;
    private Integer quantity;
    private BigDecimal price;
}
