package com.harvest.hub.controller;

import com.harvest.hub.dao.BillDao;
import com.harvest.hub.service.bill.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/bill")
public class BillController {

    private final BillService billService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody BillDao billDao,
            Principal principal
    ) {
        this.billService.create(billDao, principal);
    }

}
