package com.hao.app.dao;


import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.pojo.EmployeeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    int insert(EmployeeDO record);

    EmployeeDO selectByPrimaryKey(Integer id);

    int update(EmployeeDO record);

    int count(EmployeeQueryParam param);

    List<EmployeeDO> search(EmployeeQueryParam param);

    int applyF(@Param("id") int id, @Param("shenqing") String shenqing);

    int passdOrNot(@Param("id") int id, @Param("status") int status, @Param("descr") String descr);

    int leave(@Param("id") int id, @Param("status") int status, @Param("leaveDate") String leaveDate);
}
