package com.hao.app.manager.controller;

import com.hao.app.service.ContractProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by haoguowei.
 * Time 2017/11/30 21:16
 * Desc
 */

@Controller
@RequestMapping
public class ContractProductController extends BaseController{
    @Autowired
    private ContractProductService contractProductService;
}
