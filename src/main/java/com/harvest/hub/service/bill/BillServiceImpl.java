package com.harvest.hub.service.bill;

import com.harvest.hub.dao.BillDao;
import com.harvest.hub.entity.Bill;
import com.harvest.hub.entity.BillItem;
import com.harvest.hub.entity.Stock;
import com.harvest.hub.repository.*;
import com.harvest.hub.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final BillItemRepository billItemRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;


    @Override
    @Transactional
    public void create(BillDao billDao, Principal principal) {
        UserDetailsImpl userDetails = (UserDetailsImpl)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        billDao.getBillItemDaoList().forEach(item -> {
            Stock stock = this.stockRepository.findByActiveTrueAndProduct_Id(item.getIdProduct()).orElseThrow(() -> new IllegalArgumentException("No Stock Found for " + item.getName()));

            if(stock.getStock() < item.getQty().doubleValue()){
                throw new IllegalArgumentException("No Enough Stock Found for " + item.getName());
            }
        });

        Bill bill = Bill.builder()
                .user(this.userRepository.findByActiveTrueAndEmail(userDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException(("User not found"))))
                .amount(billDao.getPrice())
                .createdBy(userDetails.getName())
                .createdDate(LocalDateTime.now())
                .active(Boolean.TRUE)
                .build();

        this.billRepository.save(bill);

        billDao.getBillItemDaoList().forEach(item -> {
            this.billItemRepository.save(
                    BillItem.builder()
                            .product(this.productRepository.findByActiveTrueAndId(item.getIdProduct()).orElseThrow(() -> new IllegalArgumentException(("Product not found"))))
                            .qty(item.getQty())
                            .amount(item.getPrice())
                            .createdBy(userDetails.getName())
                            .createdDate(LocalDateTime.now())
                            .bill(bill)
                            .active(Boolean.TRUE)
                            .build()
            );
            Stock stock = this.stockRepository.findByActiveTrueAndProduct_Id(item.getIdProduct()).orElseThrow(() -> new IllegalArgumentException("No Stock Found for " + item.getName()));
            stock.setStock(stock.getStock() - item.getQty());
            this.stockRepository.save(stock);
        });
    }

}
