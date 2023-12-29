package com.example.productservicettsevening.inheritanceexamples.tablePerClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tbc_user")
//Tell spring about the inheritance relation by
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
}
