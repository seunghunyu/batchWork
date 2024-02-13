package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
//@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    void findByUserId(){

        String userId = "C123456";

        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);

        log.info("TEST UserInfo = {}" , userInfo);

    }

}