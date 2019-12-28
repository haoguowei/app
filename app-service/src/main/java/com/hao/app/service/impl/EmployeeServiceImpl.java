package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.EmployeeMapper;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public JsonResult<EmployeeDO> searchEmployee(EmployeeQueryParam param) {
        int count = employeeMapper.count(param);
        List<EmployeeDO> list = employeeMapper.search(param);
        return new JsonResult<>(count, list);
    }

    @Override
    public EmployeeDO getById(int id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultCodeEnum insert(EmployeeDO employee) {
        int res = employeeMapper.insert(employee);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(EmployeeDO employee) {
        EmployeeDO old = employeeMapper.selectByPrimaryKey(employee.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }

        int res = employeeMapper.update(employee);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }
}
