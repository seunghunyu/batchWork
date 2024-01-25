package com.boot.batchWork.repository.rebm;

import com.boot.batchWork.data.dsdmt.MdCustInfoDd;
import com.boot.batchWork.data.rebm.RRebmDetcExtTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebmDetcInterface extends JpaRepository<RRebmDetcExtTbl, Long> {
    RRebmDetcExtTbl findByCustId(String custId);
}
