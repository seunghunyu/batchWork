package com.boot.batchWork.repository.dsdmt;

import com.boot.batchWork.data.dsdmt.MdCustInfoDd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MdCustInfoDdReposiotry extends JpaRepository<MdCustInfoDd, Long> {
    MdCustInfoDd findByCustId(String custId);
    List<MdCustInfoDd> findByGenCd(String genCd);
    List<MdCustInfoDd> findByAgeCdLike(String ageCd);
}
