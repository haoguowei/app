package com.hao.app.service.impl;

import com.hao.app.dao.PartsReceiveSendoutMapper;
import com.hao.app.service.PartsReceiveSendoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by haoguowei.
 * Time 2017/11/30 21:14
 * Desc
 */
@Service
public class PartsReceiveSendoutServiceImpl implements PartsReceiveSendoutService {

    @Autowired
    private PartsReceiveSendoutMapper partsReceiveSendoutMapper;
}
