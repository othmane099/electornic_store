package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class IndexPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean mainSlideVisibility;
    private Boolean trendingComponentVisibility;
    private Boolean newArrivalsComponentVisibility;
    private Boolean cardServicesVisibility;

}
