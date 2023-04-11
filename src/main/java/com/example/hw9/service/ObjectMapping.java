package com.example.hw9.service;

import com.example.hw9.repository.entity.Shop;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public interface ObjectMapping {

    Shop jsonToShop(HttpServletRequest json) throws IOException;

    Shop jsonToShopBigData(HttpServletRequest json) throws IOException;
}
