package com.harvest.hub.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BillDao {

    private List<BillItemDao> billItemDaoList;
    private Double price;

}
