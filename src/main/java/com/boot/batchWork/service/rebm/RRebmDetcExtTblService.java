package com.boot.batchWork.service.rebm;

import com.boot.batchWork.data.rebm.RRebmDetcExtTbl;
import com.boot.batchWork.repository.rebm.RebmDetcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RRebmDetcExtTblService {

    @Autowired
//    @Qualifier("secondDataSource")
    private RebmDetcRepository rRebmDetcExtTblRepository;

    public RRebmDetcExtTbl findByCustId(String custId){
        return rRebmDetcExtTblRepository.findByCustId(custId);
    }

}
