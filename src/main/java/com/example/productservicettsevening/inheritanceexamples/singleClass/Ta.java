package com.example.productservicettsevening.inheritanceexamples.singleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_ta")
@DiscriminatorValue(value="3")
public class Ta extends User {
    private double averageRating;
}
