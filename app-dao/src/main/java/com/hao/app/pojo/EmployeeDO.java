package com.hao.app.pojo;

import com.hao.app.commons.entity.Dicts;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class EmployeeDO implements Serializable {

    private Integer id;

    private Integer status;

    private String name;

    private String projectsName;

    private String phone;

    private String idCard;

    private Integer projects;

    private Integer jobType;
    private String jobTypeStr;

    private Integer gender;
    private String genderStr;

    private Integer ethnic;

    private Integer age;

    private Date birthDate;

    private Date entryDate;

    private Date leaveDate;

    private Integer eduType;

    private Integer hukouType;

    private String hujiAddress;

    private String address;

    private String emergencyContact;

    private String emergencyContactPhone;

    private String safeType;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

    public String getJobTypeStr() {
        return Dicts.employeeJobTypeMap.get(this.jobType);
    }

    public String getGenderStr() {
        return Dicts.genderMap.get(this.gender);
    }
}