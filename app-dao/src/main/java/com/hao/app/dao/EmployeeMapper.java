package com.hao.app.dao;


import com.hao.app.pojo.EmployeeDO;

public interface EmployeeMapper {

    int insert(EmployeeDO record);

    EmployeeDO selectByPrimaryKey(Integer id);

    int update(EmployeeDO record);
}
