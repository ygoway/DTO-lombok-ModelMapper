package com.example.hw9.service.impl;

import com.example.hw9.repository.ShopRepository;
import com.example.hw9.repository.entity.Shop;
import com.example.hw9.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop addShop(Shop shop) {
        if (shop == null) {
            throw new IllegalArgumentException("Shop cannot be a null!");
        }
        if (shop.getId() == null) {
            throw new IllegalArgumentException("Shop id cannot be a null!");
        }
        if (shopRepository.existsById(shop.getId())) {
            throw new IllegalArgumentException("Shop with id : " + shop.getId() + " already exist!");
        }
        Shop creatingShop = shopRepository.save(shop);
        return creatingShop;
    }

    @Override
    public void deleteShopById(Long shopId) {
        if (shopId == null) {
            throw new IllegalArgumentException("Shop id cannot be a null!");
        }
        if (shopRepository.existsById(shopId)) {
            shopRepository.deleteById(shopId);
            System.out.println("Shop with id :" + shopId + " deleted successful");
        } else {
            throw new IllegalArgumentException("Shop not exist!");
        }
    }

    @Override
    public List<Shop> getAllShops() {
        List<Shop> shopList = new ArrayList<>();
        shopRepository.findAll().forEach(shopList::add);
        if (shopList.isEmpty()) {
            throw new RuntimeException("Shop list is empty");
        }
        return shopList;
    }

    @Override
    public Shop getById(Long shopID) {
        return shopRepository.findById(shopID).
                orElseThrow(() -> new IllegalArgumentException("Shop with id : " + shopID + " does not exist!"));
    }

    @Override
    public Shop updateShopById(Shop updatingShop, Long id) {
        if (updatingShop == null) {
            throw new IllegalArgumentException("Updating shop cannot be a null");
        }
        ShopService shopService = new ShopServiceImpl();
        Shop updatedShop = shopService.getById(id);
        updatedShop.setCity(updatingShop.getCity());
        updatedShop.setStreet(updatingShop.getStreet());
        updatedShop.setShopName(updatingShop.getShopName());
        updatedShop.setEmployeesCount(updatingShop.getEmployeesCount());
        updatedShop.setHasWebsite(updatingShop.isHasWebsite());
        return shopRepository.save(updatedShop);
    }
}
