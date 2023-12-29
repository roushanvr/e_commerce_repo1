package com.example.productservicettsevening.inheritanceexamples.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_mentor")
public class Mentor extends User {
    private int numberOfSessions;
    private int numberOfMentees;
}
