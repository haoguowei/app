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
        ProjectsDO old = projectsMapper.selectByPrimaryKey(obj.getId());
        if (old == null) {
            return ResultCodeEnum.FAIL_ITEM;
        }
        int res = projectsMapper.update(obj);
        if (res > 0) {
            return ResultCodeEnum.SUCCESS;
        } else {
            return ResultCodeEnum.FAIL;
        }
    }

    @Override
    public ProjectsDO getById(Integer id) {
        if (id != null && id > 0) {
            return projectsMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    @Override
    public JsonResult<ProjectsDO> search(Integer areaId) {
        List<ProjectsDO> list = projectsMapper.searchProjects(areaId);
        return new JsonResult<>(list == null ? 0 : list.size(), list);
    }
}
