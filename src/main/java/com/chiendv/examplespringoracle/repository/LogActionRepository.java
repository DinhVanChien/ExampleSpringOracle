package com.chiendv.examplespringoracle.repository;

import com.chiendv.examplespringoracle.entity.LogAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

public interface LogActionRepository extends JpaRepository<LogAction, Long> {
    //LockModeType.PESSIMISTIC_WRITE
//    @Query("select l from LogAction l where l.id = :id", lockMode = PESSIMISTIC_WRITE)
//    LogAction findActionById(@Param("id") Long id);
}
