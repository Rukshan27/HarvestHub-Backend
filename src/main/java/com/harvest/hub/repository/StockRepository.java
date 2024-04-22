package com.harvest.hub.repository;

import com.harvest.hub.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByActiveTrue();

    Optional<Stock> findByActiveTrueAndId(Long id);

    Optional<Stock> findByActiveTrueAndProduct_Id(Long id);

}
