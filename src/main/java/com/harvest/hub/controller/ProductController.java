package com.harvest.hub.controller;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dao.ProductDao;
import com.harvest.hub.entity.Product;
import com.harvest.hub.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(
            @RequestBody ProductDao productDao,
            Principal principal
    ) {
        this.productService.createProduct(productDao, principal);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(
            @PathVariable Long id,
            Principal principal
    ) {
        this.productService.deleteProduct(id, principal);
    }

    @GetMapping("get/{productType}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductsByType(
            @PathVariable ProductType productType
    ) {
        return this.productService.getProductsByType(productType);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductsById(
            @PathVariable Long id
    ) {
        return this.productService.getProductsById(id);
    }
}
