package com.hao.app.dao;


import com.hao.app.pojo.EmployeeDO;

public interface EmployeeDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeDO record);

    int insertSelective(EmployeeDO record);

    EmployeeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmployeeDO record);

    int updateByPrimaryKey(EmployeeDO record);
}