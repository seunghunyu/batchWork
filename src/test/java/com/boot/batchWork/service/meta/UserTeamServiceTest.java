package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import com.boot.batchWork.data.meta.UserTeam;
import com.boot.batchWork.data.meta.UserTeamId;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

        Assertions.assertThat(userTeamInfo)
                .isNotNull();

        log.info("userTeamInfo = {}" , userTeamInfo);
    }

    @Test
    void findAll() {

    }

    @Test
    void existsById() {

    }

    @Test
    void save() {

    }
}