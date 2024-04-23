package com.harvest.hub.service.dashboard;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dto.ChartDto;

import java.util.List;

public interface DashboardService {

    List<ChartDto> getSale(ProductType productType);

    List<ChartDto> getSaleByProduct();

}
