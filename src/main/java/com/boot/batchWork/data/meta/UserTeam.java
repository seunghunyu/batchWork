package com.boot.batchWork.data.meta;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="USER_TEAM", schema = "ecube")
@NoArgsConstructor
public class UserTeam {

    @EmbeddedId
    UserTeamId userTeamId;

    @Column(name="TEAM_NAME")
    String teamName;


    public UserTeam(UserTeamId userTeamId, String teamName) {
        this.userTeamId = userTeamId;
        this.teamName = teamName;
    }

    public static UserTeam createTeam(UserTeamId userTeamId, String teamName){
        return new UserTeam(userTeamId, teamName);
    }
}
