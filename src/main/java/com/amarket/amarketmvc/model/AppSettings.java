package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class AppSettings {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phone1;
    private String phone2;
    private String agencyAddress;
    private String agencyCity;
    @Lob
    private String agencySrcLocation;
    private String openingDays;
    private String openingTime;
    private String mailAgency;
    private String mailReceptionAgency;


}
