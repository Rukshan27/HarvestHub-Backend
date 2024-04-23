package com.harvest.hub.repository;

import com.harvest.hub.dto.ChartDto;
import com.harvest.hub.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {

    @Query(
            nativeQuery = true,
            value = """
                        SELECT
                        	SUM(BI.QTY) AS count,
                        	P.NAME      AS name	
                        FROM BILL_ITEM BI
                        INNER JOIN PRODUCT P ON P.ID = BI.FK_PRODUCT
                        WHERE P.TYPE = :productType
                        GROUP BY P.NAME
                    """
    )
    List<ChartDto> getSaleByType(String productType);

    @Query(
            nativeQuery = true,
            value = """
                        SELECT
                            SUM(BI.AMOUNT) 	AS count,
                            P.NAME      	AS name	
                        FROM BILL_ITEM BI
                        INNER JOIN PRODUCT P ON P.ID = BI.FK_PRODUCT
                        GROUP BY P.NAME
                    """
    )
    List<ChartDto> getSaleByProduct();
}
