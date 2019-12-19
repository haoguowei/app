package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.ProjectsDO;

public interface ProjectsService {

    ResultCodeEnum insert(ProjectsDO obj);

    ResultCodeEnum update(ProjectsDO obj);

    ProjectsDO getById(int id);

    JsonResult<ProjectsDO> search(int areaId);
}
