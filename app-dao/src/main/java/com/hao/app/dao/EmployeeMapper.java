package com.hao.app.dao;


import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.pojo.EmployeeDO;

import java.util.List;

public interface EmployeeMapper {

    int insert(EmployeeDO record);

    EmployeeDO selectByPrimaryKey(Integer id);

    int update(EmployeeDO record);

    int count(EmployeeQueryParam param);

    List<EmployeeDO> search(EmployeeQueryParam param);
}