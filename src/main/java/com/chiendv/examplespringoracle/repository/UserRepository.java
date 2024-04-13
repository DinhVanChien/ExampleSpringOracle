package com.chiendv.examplespringoracle.repository;

import com.chiendv.examplespringoracle.entity.Users;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("SELECT distinct u.createDate FROM Users u")
    List<Date> findAllDate();
    @Query("SELECT u FROM Users u Where u.createDate < :dateTime")
    List<Users> findAllByDateTime(@Param("dateTime") Timestamp dateTime);
}
