package com.harvest.hub.service.bill;

import com.harvest.hub.dao.BillDao;

import java.security.Principal;
import java.util.List;

public interface BillService {

    void create(BillDao billDao, Principal principal);

}
