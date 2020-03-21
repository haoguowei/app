package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AreaDO;
import com.hao.app.service.AreaService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 合同管理
 */
@Controller
public class ContractController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ContractController.class);


    @RequestMapping("/initContract.do")
    public String initContract(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/contract/initContract";
    }

    @RequestMapping("/initShuijin.do")
    public String initShuijin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/contract/initShuijin";
    }


    @RequestMapping("/initManagerPay.do")
    public String initManagerPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/contract/initManagerPay";
    }

}
