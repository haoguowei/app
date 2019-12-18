package com.hao.app.service.impl;

import com.hao.app.dao.ProjectsMapper;
import com.hao.app.service.ProjectsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Resource
    private ProjectsMapper projectsMapper;
}
