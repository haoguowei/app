package com.hao.app.service.impl;

import com.hao.app.dao.ProductPartsMapper;
import com.hao.app.service.ProductPartsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by haoguowei.
 * Time 2017/11/30 21:15
 * Desc
 */
@Service
public class ProductPartsServiceImpl implements ProductPartsService {

    @Autowired
    private ProductPartsMapper productPartsMapper;
}
