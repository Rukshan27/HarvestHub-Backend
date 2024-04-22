package com.harvest.hub.repository;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByActiveTrueAndName(String name);

    List<Product> findByActiveTrue();

    Optional<Product> findByActiveTrueAndId(Long id);

    List<Product> findByActiveTrueAndProductType(ProductType productType);

}
