package com.xzh.controller;

import com.alibaba.fastjson.JSON;
import com.xzh.entity.EsEntity;
import com.xzh.service.EsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 向振华
 * @date 2018/11/21 16:05
 */
@Api(tags = "测试")
@RequestMapping("test")
@RestController
public class TestController {

    @Resource
    private EsService esService;

    @ResponseBody
    @GetMapping("/add")
    public String add(EsEntity esEntity) {
        esService.save(esEntity);
        return "success";
    }

    @ResponseBody
    @GetMapping("/select")
    public String select(Search search) {
        List<EsEntity> entities = esService.select(search);
        return JSON.toJSONString(entities);
    }
}
