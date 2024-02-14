package com.boot.batchWork.repository.meta;

import com.boot.batchWork.data.meta.UserTeam;
import com.boot.batchWork.data.meta.UserTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, UserTeamId> {

    Optional<UserTeam> findByUserTeamId(UserTeamId userTeamId);
    List<UserTeam> findAll();

    @Override
    <S extends UserTeam> S save(S entity);

    @Override
    boolean existsById(UserTeamId userTeamId);

    @Override
    long count();

    @Override
    void delete(UserTeam entity);

}
