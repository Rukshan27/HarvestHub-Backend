package com.harvest.hub.service.product;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dao.ProductDao;
import com.harvest.hub.entity.Product;

import java.security.Principal;
import java.util.List;

public interface ProductService {

    void createProduct(ProductDao productDao, Principal principal);

    List<Product> getAllProducts();

    void deleteProduct(Long id, Principal principal);

    List<Product> getProductsByType(ProductType productType);

}
