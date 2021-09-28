package com.pashenko.eshop.entities;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
