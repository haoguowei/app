package com.hao.app.dao;


import com.hao.app.pojo.CostsTypeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CostsTypeMapper {

    CostsTypeDO selectByPrimaryKey(Integer id);

    List<CostsTypeDO> search();

    List<CostsTypeDO> searchByParentId(@Param("parentId") Integer parentId);

}
