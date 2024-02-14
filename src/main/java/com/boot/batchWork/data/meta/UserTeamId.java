package com.boot.batchWork.data.meta;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class UserTeamId implements Serializable {
    @Column(name = "TEAM_ID")
    private String teamId;
    @Column(name = "USER_ID")
    private String userId;
}
