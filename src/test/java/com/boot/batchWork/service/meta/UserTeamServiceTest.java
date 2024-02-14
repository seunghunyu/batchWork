package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import com.boot.batchWork.data.meta.UserTeam;
import com.boot.batchWork.data.meta.UserTeamId;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserTeamServiceTest {
    @Autowired
    UserTeamService userTeamService;

    @Test
    void findByUserTeamId() {
        String userId = "C123456";
        UserTeamId userTeamId = new UserTeamId();
        userTeamId.setTeamId("A");
        userTeamId.setUserId("C123456");

        Optional<UserTeam> userTeamInfo = userTeamService.findByUserTeamId(userTeamId);

        assertThat(userTeamInfo)
                .isNotNull();

        log.info("userTeamInfo = {}" , userTeamInfo);
    }

    @Test
    void findAll() {

        List<UserTeam> all = userTeamService.findAll();

        for(UserTeam team : all){
            log.info(team.toString());
        }

    }

    @Test
    void existsById() {
        String teamId = "A";
        String userId = "C123456";
        UserTeamId userTeamId = new UserTeamId();

        userTeamId.setTeamId(teamId);
        userTeamId.setUserId(userId);

        assertThat(userTeamService.existsById(userTeamId))
                .isTrue();
        log.info(" userTeam exist = {} ",userTeamId.toString());


        teamId = "A";
        userId = "C123456222222222222222222";
        userTeamId.setTeamId(teamId);
        userTeamId.setUserId(userId);
        assertThat(userTeamService.existsById(userTeamId))
                .isTrue();
        log.info(" userTeam exist = {} ",userTeamId.toString());
    }

    @Test
    void save() {

    }
}