package com.boot.batchWork.repository.meta;

import com.boot.batchWork.data.meta.UserInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserId(String userId);
    List<UserInfo> findAll();
    boolean existsByUserId(String userId);

    //JPQL
    //@Query("SELECT CASE WHEN COUNT(USER_ID) > 0 THEN TRUE ELSE FALSE END from USER_INFO WHERE USER_ID = :userId  ")
    @Query("SELECT CASE WHEN COUNT(userId) > 0 THEN TRUE ELSE FALSE END FROM UserInfo C WHERE userId = :userId  ")
    boolean existsByUserId2(@Param("userId") String userId);

    @Override
    <S extends UserInfo> S save(S entity);

    @Override
    boolean existsById(String s);

    @Override
    long count();

    @Override
    void delete(UserInfo entity);

}
