package com.harvest.hub.service.dashboard;

import com.harvest.hub.constant.enums.ProductType;
import com.harvest.hub.dto.ChartDto;
import com.harvest.hub.repository.BillItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService {

    private final BillItemRepository billItemRepository;

    @Override
    public List<ChartDto> getSale(ProductType productType) {
        return this.billItemRepository.getSaleByType(productType.name());
    }

    @Override
    public List<ChartDto> getSaleByProduct() {
        return this.billItemRepository.getSaleByProduct();
    }

}
