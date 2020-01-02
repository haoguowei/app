package com.hao.app.manager;

import com.hao.app.manager.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);


    @RequestMapping("/initKanban.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/charts/initKanban";
    }


}
