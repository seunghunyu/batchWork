package com.boot.batchWork.repository;

import com.boot.batchWork.data.MdCustInfoDd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MdCustInfoDdReposiotry extends JpaRepository<MdCustInfoDd, Long> {
    public MdCustInfoDd findByCustId(String custId);
    public List<MdCustInfoDd> findByGenCd(String genCd);
    public List<MdCustInfoDd> findByAgeCdLike(String ageCd);
}
