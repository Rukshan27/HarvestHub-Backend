package com.harvest.hub.controller;

import com.harvest.hub.dao.StockDao;
import com.harvest.hub.dto.StockDto;
import com.harvest.hub.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStock(
            @RequestBody StockDao stockDao,
            Principal principal
    ) {
        this.stockService.createStock(stockDao, principal);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<StockDto> getAllStocks() {
        return this.stockService.getAllStocks();
    }

    @PostMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStock(
            @PathVariable Long id,
            Principal principal
    ) {
        this.stockService.deleteStock(id, principal);
    }

    @PutMapping("alert/{id}/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAlertLimit(
            @PathVariable Long id,
            @PathVariable Double limit,
            Principal principal
    ) {
        this.stockService.updateAlertLimit(id, limit, principal);
    }

    @PutMapping("{id}/{limit}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStockLimit(
            @PathVariable Long id,
            @PathVariable Double limit,
            Principal principal
    ) {
        this.stockService.updateStockLimit(id, limit, principal);
    }

}
