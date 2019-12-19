package com.hao.app.service.impl;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.FinanceMapper;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.service.FinanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private FinanceMapper financeMapper;

    
}
