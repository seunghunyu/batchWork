package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    @DisplayName("find User Info by UserId")
    void findByUserId(){

        String userId = "C123456";

        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);

        log.info("TEST UserInfo = {}" , userInfo);

    }

    @Test
    @DisplayName("Find All users in user_info table")
    void findAll(){
        List<UserInfo> all = userInfoService.findAll();

        for(UserInfo user : all){
            log.info(user.toString());
        }

    }

    @Test
    @DisplayName("User Exists Yn")
    void existsByUserId(){
        String userId = "C123456" ;
        String notUserId = "A000000000";

        Assertions.assertThat(userInfoService.existsByUserId(userId))
                .isTrue();

        Assertions.assertThat(userInfoService.existsByUserId(notUserId))
                .isTrue();


    }

    @Test
    @DisplayName("JPQL User Exists Yn")
    void existsByUserId2(){
        String userId = "C123456" ;
        String notUserId = "A000000000";

//        Assertions.assertThat(userInfoService.existsByUserId2(userId))
//                .isTrue();

        Assertions.assertThat(userInfoService.existsByUserId2(notUserId))
                .isTrue();


    }

}