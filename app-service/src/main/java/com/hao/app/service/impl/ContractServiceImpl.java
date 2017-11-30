package com.hao.app.service.impl;

import com.hao.app.dao.ContractMapper;
import com.hao.app.service.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by haoguowei.
 * Time 2017/11/30 21:14
 * Desc
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper contractMapper;
}
