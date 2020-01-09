package com.hao.app.manager.controller;

import com.hao.app.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class PayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PayController.class);

    @Resource
    private PayService payService;


}
