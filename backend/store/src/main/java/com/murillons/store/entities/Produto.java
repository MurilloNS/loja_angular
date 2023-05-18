package com.murillons.store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    @Column(nullable = false)
    private String description;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
}
