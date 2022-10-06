package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class SocialMediaPublication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String facebookLink;
    @Lob
    private String instagramLink;
    private Boolean display;
}
