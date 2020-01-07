package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.commons.utils.IdCardUtils;
import com.hao.app.dao.EmployeeMapper;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.service.EmployeeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public JsonResult<EmployeeDO> searchEmployee(EmployeeQueryParam param) {
        int count = employeeMapper.count(param);
        List<EmployeeDO> list = employeeMapper.search(param);

        if (list != null) {
            for (EmployeeDO item : list) {
                fill(item);
            }
        }

        return new JsonResult<>(count, list);
    }

    @Override
    public EmployeeDO getById(int id) {
        EmployeeDO item = employeeMapper.selectByPrimaryKey(id);
        fill(item);
        return item;
    }

    private void fill(EmployeeDO item) {
        if (item != null) {
            Map<String, String> map = IdCardUtils.getBirAgeSex(item.getIdCard());
            if (map != null) {
                item.setBirthDate(map.get("birthday"));
                item.setAge(NumberUtils.isNumber(map.get("age")) ? Integer.valueOf(map.get("age")) : null);
                String sex = map.get("sexCode");

                item.setGenderStr(StringUtils.isNotBlank(sex) && sex.equals("F") ? "女" : "男");
            }
        }
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

    @Override
    public boolean applyF(int id, String shenqing) {
        int res = employeeMapper.applyF(id, shenqing);
        return res > 0;
    }

    @Override
    public boolean passd(int id, String descr) {
        int res = employeeMapper.passd(id, descr);
        return res > 0;
    }

    @Override
    public boolean noPassed(int id, String descr) {
        int res = employeeMapper.noPassed(id, descr);
        return res > 0;
    }
}
