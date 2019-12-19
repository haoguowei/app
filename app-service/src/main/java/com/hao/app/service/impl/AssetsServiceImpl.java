package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.AssetsMapper;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;

    
}
