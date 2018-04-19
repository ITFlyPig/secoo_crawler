package com.wangyuelin.app.controller;

import com.wangyuelin.app.bean.User;
import com.wangyuelin.app.crawler.ProductProcessor;
import com.wangyuelin.app.crawler.page.CategoryPage;
import com.wangyuelin.app.service.CategoryService;
import com.wangyuelin.app.service.itf.ITest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ITest test;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductProcessor productProcessor;

    @RequestMapping("/getOneUser")
    @ResponseBody
    public User getOneUser(){
        logger.info("getOneUser");
        return test.getUser();
    }


    @RequestMapping("/getAll")
    @ResponseBody
    public List<User> getAll(){
        logger.info("getOneUser");
        return test.getAll();
    }

    @RequestMapping("/start")
    @ResponseBody
    public String start(){
        Spider.create(productProcessor).addPipeline(new ConsolePipeline()).addUrl("http://www.secoo.com/t").thread(5).run();
        return "开始爬取";

    }

}
