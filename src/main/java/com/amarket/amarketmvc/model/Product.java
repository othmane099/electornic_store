package com.amarket.amarketmvc.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String slug;
    @Lob
    private String description;
    private String price;
    private String defaultPicture;
    private Long views;
    @OneToOne(cascade = CascadeType.ALL)
    private Discount discount;
    @OneToOne(cascade = CascadeType.ALL)
    private SocialMediaPublication socialMediaPublication;
    @OneToOne(cascade = CascadeType.ALL)
    private Video video;
    private Boolean trending;
    private Boolean newArrival;
    @OneToOne(cascade = CascadeType.ALL)
    private Quantity quantity;
    @OneToMany(mappedBy="product")
    private Set<Picture> pictures = new HashSet<>();
    private Boolean visibility;
    @ManyToOne
    private Category category;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant dateCreation;
    @UpdateTimestamp
    private Instant dateUpdate;

    public boolean hasDiscount(){
        return this.discount == null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
