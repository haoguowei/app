package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.EmployeeDO;

public interface EmployeeService {

    JsonResult<EmployeeDO> searchEmployee();

    EmployeeDO getById(int id);

    ResultCodeEnum insert(EmployeeDO employee);

    ResultCodeEnum update(EmployeeDO employee);
}