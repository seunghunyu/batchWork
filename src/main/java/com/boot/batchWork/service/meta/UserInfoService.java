package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import com.boot.batchWork.repository.meta.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public Optional<UserInfo> findByUserId(String userId){
            return userInfoRepository.findByUserId(userId);
        }

    public List<UserInfo> findAll(){
        return userInfoRepository.findAll();
    }

    public Boolean existsById(String userId){
        return userInfoRepository.existsById(userId);
    }

    public Boolean existsByUserId(String userId){
        return userInfoRepository.existsByUserId(userId);
    }

    public Boolean existsByUserId2(String userId){
        return userInfoRepository.existsByUserId2(userId);
    }

    public UserInfo save(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }


}
