package com.hao.app.dao;


import com.hao.app.pojo.ProjectsDO;

public interface ProjectsDOMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(ProjectsDO record);

    int insertSelective(ProjectsDO record);

    ProjectsDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectsDO record);

    int updateByPrimaryKey(ProjectsDO record);
}