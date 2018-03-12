package com.example.employee;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lastName;
    private String firstName;

    public Employee() {
    }

    public Employee(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
