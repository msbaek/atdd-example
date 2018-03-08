package com.example.employee;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;
    private String firstName;

    public Employee() {
    }

    public Employee(String lastName, String firstName) {
    }
}
