package com.harvest.hub.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillItemDao {

    private Long idProduct;
    private String name;
    private Long qty;
    private Double price;

}
