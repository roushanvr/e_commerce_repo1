package com.example.productservicettsevening.inheritanceexamples.joinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_user")
//Tell spring about the inheritance relation by
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;
    private String password;
}
