package com.example.hw9.web;

import com.example.hw9.repository.entity.Shop;
import com.example.hw9.service.ObjectMapping;
import com.example.hw9.service.ShopConverter;
import com.example.hw9.service.ShopService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    public ShopService shopService;

    @Autowired
    public ShopConverter shopConverter;

    @Autowired
    public ObjectMapping objectMapping;

    /*response without employeesCount, based on shopDto*/

    @PostMapping("/create")
    public ResponseEntity createShop(HttpServletRequest request) {
        try {
            Shop shop = objectMapping.jsonToShop(request);
            shopService.addShop(shop);
            return ResponseEntity.status(HttpStatus.CREATED).body(shopConverter.convertToShopDto(shop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new IllegalArgumentException("Invalid request data"));
        }
    }

    @DeleteMapping("/delete/{shopID}")
    public ResponseEntity deleteShop(@PathVariable("shopID") Long shopID) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Shop with id : " + shopID + " delete successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectNotFoundException("Shop with id : " + shopID + "not found", shopService.getById(shopID)));
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllShops() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(shopConverter.convertListShopToListShopDto(shopService.getAllShops()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new IndexOutOfBoundsException("Sorry, none shops available"));
        }
    }

    @GetMapping("/{shopID}")
    public ResponseEntity getShopById(@PathVariable("shopID") Long shopID) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(shopConverter.convertToShopDto(shopService.getById(shopID)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new IllegalArgumentException("Shop with id : " + shopID + " not found"));
        }

    }

    @PatchMapping("/{shopID}")
    public ResponseEntity patchShopById(@PathVariable("shopId") HttpServletRequest request, Long shopID) {
        try {
            Shop shop = objectMapping.jsonToShop(request);
            shopService.updateShopById(shop, shopID);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(shopConverter.convertToShopDto(shop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(new IllegalArgumentException("Invalid request data"));
        }
    }
}
