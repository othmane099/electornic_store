package com.amarket.amarketmvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Category {

    public Category(boolean visibility, boolean trending) {
        this.visibility = visibility;
        this.trending = trending;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String slug;
    private String dataFilter;
    private Boolean trending;
    private Boolean visibility;
    @JsonIgnore
    @OneToMany(mappedBy="category")
    private List<Product> products = new ArrayList<>();



    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", trending=" + trending +
                ", visibility=" + visibility +
                '}';
    }
}
