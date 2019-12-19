package com.hao.app.dao;


import com.hao.app.pojo.ProjectsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectsMapper {

    int insert(ProjectsDO record);

    ProjectsDO selectByPrimaryKey(Integer id);

    int update(ProjectsDO record);

    List<ProjectsDO> searchProjects(@Param("areaId") Integer areaId);
}