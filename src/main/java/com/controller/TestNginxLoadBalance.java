package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.entity.UserInfo;
import com.mapper.UserInfoMapper;
import com.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TestNginxLoadBalance {
    private static final Logger log = LoggerFactory.getLogger(TestNginxLoadBalance.class);

    @Autowired
    private UserInfoService  userInfoService;



    @GetMapping("/nginx")
    public IPage user(String condition){


        Page p = new Page(0,10);
        QueryWrapper queryWrapper = new QueryWrapper();
        IPage UserInfos  =userInfoService.pageMaps(p,queryWrapper) ;
        log.debug("nginx2--------------------------------------");


      return UserInfos;
    }

}
