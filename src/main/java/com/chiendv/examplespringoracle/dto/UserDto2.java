package com.chiendv.examplespringoracle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class UserDto2 {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("taskVn")
    private String taskVn;
    @JsonProperty("task")
    private String task;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("groups")
    private String groups;
    @JsonProperty("createData")
    private Date createDate;
    @JsonProperty("createDataTime")
    private Date createDataTime;
    public UserDto2(){}

    public UserDto2(Long id, String name, String phone, String groups, Date createDate, Date createDataTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.groups = groups;
        this.createDate = createDate;
        this.createDataTime = createDataTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDataTime() {
        return createDataTime;
    }

    public void setCreateDataTime(Date createDataTime) {
        this.createDataTime = createDataTime;
    }

    public String getTaskVn() {
        return taskVn;
    }

    public void setTaskVn(String taskVn) {
        this.taskVn = taskVn;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
