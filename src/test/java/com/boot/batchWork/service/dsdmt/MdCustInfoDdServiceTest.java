package com.boot.batchWork.service.dsdmt;

import com.boot.batchWork.data.dsdmt.MdCustInfoDd;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class MdCustInfoDdServiceTest {

    @Autowired
    MdCustInfoDdService mdCustInfoDdService;

    @Test
    void getId(){
        String custId = "C0000000087";
        //Optional<List<MdCustInfoDdVo>> custList = mdCustInfoDdService.findById(custId);
        MdCustInfoDd custInfo = mdCustInfoDdService.findById(custId);

        if(custInfo == null){
            //log.info("custList is not empty");
            System.out.println("custList is empty");
        }else{
            System.out.println("cust_id = " + custInfo.getCustId() + " / age = "+ custInfo.getAge() ) ;
//            log.info("custList is empty");
            //System.out.println("custList is empty");
        }

    }
}