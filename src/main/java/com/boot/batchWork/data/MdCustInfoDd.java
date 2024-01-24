package com.boot.batchWork.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="MD_CUST_INFO_DD", schema = "dsdmt")
public class MdCustInfoDd {
    @Id
    @Column(name="CUST_ID")
    String custId;
    @Column(name="GEN_CD")
    String genCd;
    @Column(name="BTHDD")
    String bthDd;
    @Column(name="AGE")
    long age;
    @Column(name="AGE_CD")
    String ageCd;
    @Column(name="HOME_ZIPNO")
    String homeZipno;
    @Column(name="HOME_SGG_CD")
    String homeSggCd;
    @Column(name="HOME_EMD_CD")
    String homeEmdCd;
    @Column(name="EXEMP_YN")
    String exempYn;
}
