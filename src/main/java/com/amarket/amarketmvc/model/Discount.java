package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oldPrice;
    private Boolean display;
    private String percentage;
    @OneToOne
    private Product product;

}
