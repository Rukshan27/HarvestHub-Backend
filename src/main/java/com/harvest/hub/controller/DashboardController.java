package com.harvest.hub.controller;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dto.ChartDto;
import com.harvest.hub.service.dashboard.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("{productType}")
    @ResponseStatus(HttpStatus.OK)
    public List<ChartDto> getSale(
            @PathVariable ProductType productType
    ) {
        return this.dashboardService.getSale(productType);
    }

    @GetMapping("product")
    @ResponseStatus(HttpStatus.OK)
    public List<ChartDto> getSaleByProduct() {
        return this.dashboardService.getSaleByProduct();
    }

}
