package com.harvest.hub.service.stock;

import com.harvest.hub.dao.StockDao;
import com.harvest.hub.dto.StockDto;

import java.security.Principal;
import java.util.List;

public interface StockService {

    void createStock(StockDao stockDao, Principal principal);

    List<StockDto> getAllStocks();

    void deleteStock(Long id, Principal principal);

    void updateAlertLimit(Long id, Double limit, Principal principal);

    void updateStockLimit(Long id, Double limit, Principal principal);

}
