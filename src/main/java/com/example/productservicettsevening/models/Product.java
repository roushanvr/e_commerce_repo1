package com.example.productservicettsevening.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModels{
    private String title;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;

}
