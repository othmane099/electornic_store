package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Quantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private Boolean display;
    @Enumerated
    private QuantityBadge quantityBadge;

    @OneToOne
    private Product product;
}
