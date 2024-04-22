package com.harvest.hub.service.stock;

import com.harvest.hub.dao.StockDao;
import com.harvest.hub.dto.StockDto;
import com.harvest.hub.entity.Stock;
import com.harvest.hub.repository.ProductRepository;
import com.harvest.hub.repository.StockRepository;
import com.harvest.hub.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    @Override
    public void createStock(StockDao stockDao, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.stockRepository.findByActiveTrueAndProduct_Id(stockDao.getProduct()).ifPresentOrElse(
                stock -> {
                    throw new IllegalArgumentException("Stock already exist for name: " + stock.getProduct().getName());
                }, () -> {
                    this.stockRepository.save(
                            Stock.builder()
                                    .product(this.productRepository.findByActiveTrueAndId(stockDao.getProduct()).orElseThrow(() -> new IllegalArgumentException("Product doesnt exist")))
                                    .limit(stockDao.getLimit())
                                    .stock(stockDao.getStock())
                                    .active(Boolean.TRUE)
                                    .createdBy(userDetails.getName())
                                    .createdDate(LocalDateTime.now())
                                    .build()
                    );
                }
        );
    }

    @Override
    public List<StockDto> getAllStocks() {
        return this.stockRepository.findByActiveTrue()
                .stream()
                .map(StockDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStock(Long id, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.stockRepository.findByActiveTrueAndId(id).ifPresentOrElse(
                stock -> {
                    stock.setActive(Boolean.FALSE);
                    stock.setUpdatedBy(userDetails.getName());
                    stock.setUpdatedDate(LocalDateTime.now());
                    this.stockRepository.save(stock);
                }, () -> {
                    throw new IllegalArgumentException("No Stock Found");
                }
        );
    }

    @Override
    public void updateAlertLimit(Long id, Double limit, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.stockRepository.findByActiveTrueAndId(id).ifPresentOrElse(
                stock -> {
                    stock.setLimit(limit);
                    stock.setUpdatedBy(userDetails.getName());
                    stock.setUpdatedDate(LocalDateTime.now());
                    this.stockRepository.save(stock);
                }, () -> {
                    throw new IllegalArgumentException("No Stock Found");
                }
        );
    }

    @Override
    public void updateStockLimit(Long id, Double limit, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        this.stockRepository.findByActiveTrueAndId(id).ifPresentOrElse(
                stock -> {
                    stock.setStock(stock.getStock() + limit);
                    stock.setUpdatedBy(userDetails.getName());
                    stock.setUpdatedDate(LocalDateTime.now());
                    this.stockRepository.save(stock);
                }, () -> {
                    throw new IllegalArgumentException("No Stock Found");
                }
        );
    }
}
