package com.chiendv.examplespringoracle.service;

import com.chiendv.examplespringoracle.dto.UserDto;
import com.chiendv.examplespringoracle.entity.Users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UsersService {
    List<UserDto> getUserSearch(Long group, String name, Map<String, Long> mapCount);
    List<UserDto> findAll();
    String export() throws IOException;

    String testJob();

    void saveList(List<UserDto> userDtos);
    void testStore(String name);

    List<Users> testCache() throws InterruptedException;
    List<Date> getAllDate();

    void save(UserDto userDto);

    Users update(UserDto userDto);

    List<Users> findAllByDateTime();
}
