package com.boot.batchWork.repository;

import com.boot.batchWork.data.MdCustInfoDdVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MdCustInfoDdReposiotry extends JpaRepository<MdCustInfoDdVo, Long> {
    public List<MdCustInfoDdVo> findByCustId(String custId);
    public List<MdCustInfoDdVo> findByGenCd(String genCd);
    public List<MdCustInfoDdVo> findByAgeCdLike(String ageCd);
}
