package com.example.productservicettsevening.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseModels {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date created_At;
    private Date lastUpdated_At;
    private boolean isDeleted;
}
