package com.boot.batchWork.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Data
@RequiredArgsConstructor
@Entity(name="md_cust_info_dd")
public class MdCustInfoDdVo {
    String custId;
    String genCd;
    String bthDd;
    long age;
    String ageCd;
    String homeZipNo;
    String homeSggCd;
    String homeEmdCd;
    String execmpYn;
}
