package com.boot.batchWork.data.meta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="USER_INFO", schema = "ecube")
@NoArgsConstructor
public class UserInfo {
    @Id
    @Column(name="USER_ID")
    String userId;
    @Column(name="PASSWORD")
    String password;
    @Column(name="USER_ID_SAVE_YN")
    String userIdSaveYn;

    public UserInfo(String userId, String password, String userIdSaveYn) {
        this.userId = userId;
        this.password = password;
        this.userIdSaveYn = userIdSaveYn;
    }

    public static UserInfo createUser(String userId, String password, String userIdSaveYn){
        return new UserInfo(userId, password, userIdSaveYn);
    }
}
