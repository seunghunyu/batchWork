package com.boot.batchWork.service.meta;

import com.boot.batchWork.data.meta.UserInfo;
import com.boot.batchWork.data.meta.UserTeam;
import com.boot.batchWork.data.meta.UserTeamId;
import com.boot.batchWork.repository.meta.UserInfoRepository;
import com.boot.batchWork.repository.meta.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTeamService {

    @Autowired
    private UserTeamRepository userTeamRepository;

    public Optional<UserTeam> findByUserTeamId(UserTeamId userTeamId){
            return userTeamRepository.findByUserTeamId(userTeamId);
    }

    public List<UserTeam> findAll(){
        return userTeamRepository.findAll();
    }

    public Boolean existsById(UserTeamId userTeamId){
        return userTeamRepository.existsById(userTeamId);
    }

    public UserTeam save(UserTeam userTeam){
        return userTeamRepository.save(userTeam);
    }


}
