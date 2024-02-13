package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import com.boot.batchWork.repository.meta.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    @Autowired
    UserInfoRepository userInfoRepository;

    Optional<UserInfo> findByUserId(String userId){
        return userInfoRepository.findByUserId(userId);
    }
}
