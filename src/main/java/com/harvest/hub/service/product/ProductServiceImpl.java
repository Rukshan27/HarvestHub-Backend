package com.harvest.hub.service.product;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dao.ProductDao;
import com.harvest.hub.entity.Product;
import com.harvest.hub.repository.ProductRepository;
import com.harvest.hub.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductDao productDao, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.productRepository.findByActiveTrueAndName(productDao.getName()).ifPresentOrElse(
                product -> {
                    throw new IllegalArgumentException("Product already exist for name: " + product.getName());
                }, () -> {
                    this.productRepository.save(
                            Product.builder()
                                    .name(productDao.getName())
                                    .price(productDao.getPrice())
                                    .photo(productDao.getPhoto())
                                    .productType(productDao.getProductType())
                                    .discount(productDao.getDiscount())
                                    .active(Boolean.TRUE)
                                    .createdBy(userDetails.getName())
                                    .createdDate(LocalDateTime.now())
                                    .build()
                    );
                }
        );
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findByActiveTrue();
    }

    @Override
    public void deleteProduct(Long id, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.productRepository.findByActiveTrueAndId(id).ifPresentOrElse(
                product -> {
                    product.setActive(Boolean.FALSE);
                    product.setUpdatedBy(userDetails.getName());
                    product.setUpdatedDate(LocalDateTime.now());
                    this.productRepository.save(product);
                }, () -> {
                    throw new IllegalArgumentException("No Product Found");
                }
        );
    }

    @Override
    public List<Product> getProductsByType(ProductType productType) {
        return this.productRepository.findByActiveTrueAndProductType(productType);
    }

    @Override
    public Product getProductsById(Long id) {
        return  this.productRepository.findByActiveTrueAndId(id).orElseThrow(
                () -> new IllegalArgumentException("No Product Found")
        );
    }

}
