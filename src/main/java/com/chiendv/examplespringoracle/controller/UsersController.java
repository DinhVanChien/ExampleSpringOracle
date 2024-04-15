package com.chiendv.examplespringoracle.controller;

import com.chiendv.examplespringoracle.dto.UserDto;
import com.chiendv.examplespringoracle.dto.UserDto2;
import com.chiendv.examplespringoracle.dto.UserRequest;
import com.chiendv.examplespringoracle.entity.Users;
import com.chiendv.examplespringoracle.service.LogActionService;
import com.chiendv.examplespringoracle.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersService usersService;
    private final LogActionService logActionService;
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    public UsersController(UsersService usersService,
                           LogActionService logActionService) {
        this.usersService = usersService;
        this.logActionService = logActionService;
    }
    @PostMapping("/search")
    public List<UserDto> getUserSearch(@RequestBody UserRequest userRequest) {
        Map<String, Long> mapCount = new HashMap<>();
        List<UserDto> userDtos = null;
        for(int i = 0; i < 15; i++) {
            userDtos = usersService.getUserSearch(userRequest.getGroup(), userRequest.getName(), mapCount);
        }
        System.out.println(mapCount.get("COUNT"));
        return userDtos;
    }

    @PostMapping("/save-all")
    public void saveAll(@RequestBody List<UserDto> userRequests) {
         usersService.saveList(userRequests);
    }

    @GetMapping("/find-all")
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = usersService.findAll();
        return userDtos;
    }
    @GetMapping("/export")
    public String export() throws IOException {
        return usersService.export();
    }

    @GetMapping("/mapper")
    public List<UserDto2> mapper() throws IOException {
        List<UserDto> userDtos1 = new ArrayList<>();
        List<UserDto2> userDtos2 = new ArrayList<>();
        UserDto userDto1 = new UserDto(100L, "Chien", "886699", "0", new Date(), new Date());
        UserDto userDto2 = new UserDto(100L, "Hoa", "886699", "1", new Date(), new Date());
        userDtos1.add(userDto1);
        userDtos1.add(userDto2);
        ModelMapper modelMapper = new ModelMapper();
        for (UserDto user : userDtos1) {
            UserDto2 userDt = new UserDto2();
            modelMapper.map(user, userDt);
            userDtos2.add(userDt);
        }
        return userDtos2;
    }
    @GetMapping("/test-job")
    public String testJob() {
        return usersService.testJob();
    }

    @PostMapping("/test-store")
    public void testStore(@RequestBody UserRequest userRequest) {
        usersService.testStore(userRequest.getName());
    }


    @GetMapping("/user-all")
    public List<Users> allUsers() throws InterruptedException {
       return usersService.testCache();
    }
    @GetMapping("/get-all-date")
    public List<Date> getAllDate() {
        return usersService.getAllDate();
    }

    @PostMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        Users users = usersService.update(userDto);
        UserDto userDto1 =  modelMapper.map(users, UserDto.class);
        return userDto1;
    }

    @GetMapping("/get-by-datetime")
    public List<Users> update() {
        return usersService.findAllByDateTime();
    }

    @GetMapping("/get-map")
    public UserDto2 revert() {
        UserDto userDto2 = new UserDto();
        userDto2.setId(1l);
        userDto2.setName("Chien");
        userDto2.setPhone("0868866994");
        userDto2.setTask("TASK-EN");
        userDto2.setCreateDataTime(new Date());
        return modelMapper.map(userDto2, UserDto2.class);
    }

}
