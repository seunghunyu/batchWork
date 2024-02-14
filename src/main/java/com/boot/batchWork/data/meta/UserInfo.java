package com.boot.batchWork.data.meta;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="USER_INFO", schema = "ecube")
public class UserInfo {
    @Id
    @Column(name="USER_ID")
    String userId;
    @Column(name="PASSWORD")
    String password;
    @Column(name="USER_ID_SAVE_YN")
    String userIdSaveYn;
}
