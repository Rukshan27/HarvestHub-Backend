package com.harvest.hub.dao;

import com.harvest.hub.constant.enums.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDao {

    private String name;
    private Double price;
    private Double discount;
    private String photo;
    private ProductType productType;

}
