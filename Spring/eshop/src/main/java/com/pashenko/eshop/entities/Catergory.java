package com.pashenko.eshop.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Catergory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
