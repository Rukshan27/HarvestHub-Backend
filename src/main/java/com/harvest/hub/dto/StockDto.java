package com.harvest.hub.dto;

import com.harvest.hub.entity.Stock;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class StockDto {

    private Long id;
    private String product;
    private Double limit;
    private Double stock;

    public StockDto(Stock stock){
        BeanUtils.copyProperties(stock, this);
        this.product = stock.getProduct().getName();
    }
}
