package com.example.hw9.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ShopDto {

    private Long id;
    private String city;
    private String street;
    private String shopName;
    private boolean hasWebsite;
}
