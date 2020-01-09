package com.hao.app.service.impl;

import com.hao.app.dao.PayDetailMapper;
import com.hao.app.dao.PayMapper;
import com.hao.app.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Resource
    private PayDetailMapper payDetailMapper;
}
