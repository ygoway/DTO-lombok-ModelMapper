package com.example.hw9.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private String shopName;
    private int employeesCount;
    private boolean hasWebsite;
}