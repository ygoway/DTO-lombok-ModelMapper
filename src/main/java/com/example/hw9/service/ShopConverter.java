package com.example.hw9.service;

import com.example.hw9.dto.ShopDto;
import com.example.hw9.repository.entity.Shop;

import java.util.List;

public interface ShopConverter {

    ShopDto convertToShopDto(Shop shop);

    Shop convertToShopFromDto(ShopDto shopDto);

    List<ShopDto> convertListShopToListShopDto(List<Shop> shops);
}

