package com.boot.batchWork.data.rebm;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name="R_REBM_DETC_EXT_TBL", schema = "ECUBEEBM")
public class RRebmDetcExtTbl {
    @Id
    Timestamp rg_Dtms;

    @Column(name="CUST_ID")
    String custId;
    String ex_Work_Cd;
    String crd_Aflt_Cd;
    @Column(name="CARD_NO")
    String cardNo;
    @Column(name="MBMCH_NO")
    String mbmchNo;
    @Column(name="LMT_AMT")
    int lmtAmt;
    @Column(name="ALNC_AMT")
    int alncAmt;
    @Column(name="MAK_AREA")
    String makArea;
    @Column(name="BUSI_TYPE")
    String busiType;
    @Column(name="BRANCH_CD")
    String branchCd;
    @Column(name="SERVICE_CD")
    String serviceCd;
}
