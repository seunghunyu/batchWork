package com.boot.batchWork.repository.meta;

import com.boot.batchWork.data.meta.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserId(String UserId);

    @Override
    <S extends UserInfo> S save(S entity);

    @Override
    Optional<UserInfo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(UserInfo entity);



}
