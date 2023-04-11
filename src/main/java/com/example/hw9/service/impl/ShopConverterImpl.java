package com.example.hw9.service.impl;

import com.example.hw9.dto.ShopDto;
import com.example.hw9.repository.entity.Shop;
import com.example.hw9.service.ShopConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShopConverterImpl implements ShopConverter {
    @Override
    public ShopDto convertToShopDto(Shop shop) {
        return Stream.of(new ShopDto())
                .peek(shopDto -> shopDto.setId(shop.getId()))
                .peek(shopDto -> shopDto.setCity(shop.getCity()))
                .peek(shopDto -> shopDto.setStreet(shop.getStreet()))
                .peek(shopDto -> shopDto.setShopName(shop.getShopName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("New parameters are invalid"));
    }

    @Override
    public Shop convertToShopFromDto(ShopDto shopDto) {
        return Stream.of(new Shop())
                .peek(shop -> shop.setId(shopDto.getId()))
                .peek(shop -> shop.setCity(shopDto.getCity()))
                .peek(shop -> shop.setStreet(shopDto.getStreet()))
                .peek(shop -> shop.setShopName(shopDto.getShopName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("New parameters are invalid"));
    }

    @Override
    public List<ShopDto> convertListShopToListShopDto(List<Shop> shops) {
        return shops.stream().map(shop -> {
            ShopDto shopDto = new ShopDto();
            shopDto.setId(shop.getId());
            shopDto.setCity(shop.getCity());
            shopDto.setStreet(shop.getStreet());
            shopDto.setShopName(shop.getShopName());
            return shopDto;
        }).collect(Collectors.toList());
    }
}
