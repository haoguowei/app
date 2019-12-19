package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.ProjectsMapper;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.ProjectsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Resource
    private ProjectsMapper projectsMapper;

    @Override
    public ResultCodeEnum insert(ProjectsDO obj) {
        int res = projectsMapper.insert(obj);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ResultCodeEnum update(ProjectsDO obj) {
        int res = projectsMapper.update(obj);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ProjectsDO getById(int id) {
        return projectsMapper.selectByPrimaryKey(id);
    }

    @Override
    public JsonResult<ProjectsDO> search(Integer areaId) {
        List<ProjectsDO> list = projectsMapper.searchProjects(areaId);
        return new JsonResult<>(list == null ? 0 : list.size(), list);
    }
}
