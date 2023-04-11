package com.example.hw9.service.impl;

import com.example.hw9.repository.entity.Shop;
import com.example.hw9.service.ObjectMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class ObjectMappingImpl implements ObjectMapping {

    @Override
    public Shop jsonToShop(HttpServletRequest json) throws IOException { // more readable, use to small amount of data
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = mapper.readValue(json.getReader(), Shop.class);
        return shop;
    }

    @Override
    public Shop jsonToShopBigData(HttpServletRequest json) throws IOException { // better use with big data
        BufferedReader reader = json.getReader();
        String jsonShop = reader.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = mapper.readValue(jsonShop, Shop.class);
        return shop;
    }

}
