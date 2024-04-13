package com.chiendv.examplespringoracle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
public class UserDto implements Cloneable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("groups")
    private String groups;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("createData")
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date createDate;
    @JsonProperty("createDataTime")
    private Date createDataTime;
    private Date updateDate;
    public UserDto(){}
    public UserDto(String name, String phone, String groups) {
        this.name = name;
        this.phone = phone;
        this.groups = groups;
    }
    public UserDto(String name, String phone, String groups, Date createDate) {
        this.name = name;
        this.phone = phone;
        this.groups = groups;
        this.createDate = createDate;
    }

    public UserDto(Long id, String name, String phone, String groups, Date createDate, Date createDataTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.groups = groups;
        this.createDate = createDate;
        this.createDataTime = createDataTime;
    }

    public Date getCreateDataTime() {
        return createDataTime;
    }

    public void setCreateDataTime(Date createDataTime) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
