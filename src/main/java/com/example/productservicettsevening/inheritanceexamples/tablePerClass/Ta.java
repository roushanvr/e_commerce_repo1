package com.example.productservicettsevening.inheritanceexamples.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_ta")
public class Ta extends User {
    private double averageRating;
}
