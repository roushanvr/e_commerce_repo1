package com.example.productservicettsevening.inheritanceexamples.singleClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="st_user")
//Tell spring about the inheritance relation by
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//In this you also need a column which will identity which row is for user which is for ta etc..
@DiscriminatorColumn(
        name="user_type",
        discriminatorType = DiscriminatorType.INTEGER
        )
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
}
