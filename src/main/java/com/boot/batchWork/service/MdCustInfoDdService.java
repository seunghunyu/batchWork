package com.boot.batchWork.service;

import com.boot.batchWork.data.MdCustInfoDd;
import com.boot.batchWork.repository.MdCustInfoDdReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MdCustInfoDdService {

    @Autowired
    private MdCustInfoDdReposiotry mdCustInfoDdReposiotry;

    public List<MdCustInfoDd> findAll(){
        List<MdCustInfoDd> custs = new ArrayList<>();
        mdCustInfoDdReposiotry.findAll().forEach(e -> custs.add(e));
        return custs;
    }

    public MdCustInfoDd findById(String custId){
        return mdCustInfoDdReposiotry.findByCustId(custId);
    }

}
