package com.controller;

import com.ElaticMapper.EsUserRepository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.entity.UserInfo;
import com.google.common.collect.Lists;
import com.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
public class EsBlogController {

    @Autowired
    private UserInfoMapper userMapper;

    @Autowired
    private EsUserRepository esUserRepository;

    @GetMapping("/insert/all")
    public ResponseEntity loadAllUser(){
        QueryWrapper queryWrapper = new QueryWrapper();
        List<UserInfo> users = userMapper.selectList(queryWrapper);
        log.info("users:{}",users);
        users.stream().forEach(userInfo -> {
            esUserRepository.save(userInfo);
        });
        return ResponseEntity.ok(true);
    }
    @GetMapping("/search")
    public ResponseEntity getByName(String name, Pageable pageable){

        //按name进行搜索
        MatchQueryBuilder builder = QueryBuilders.matchQuery("userName", name);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Page<UserInfo> users = esUserRepository.search(builder, pageable);
        esUserRepository.search(builder);

        //迭代器转list
        ArrayList<UserInfo> list = Lists.newArrayList(users);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/del")
    public ResponseEntity.BodyBuilder del(String name, Pageable pageable){

        //按name进行搜索
        MatchQueryBuilder builder = QueryBuilders.matchQuery("userName", name);

        //如果实体和数据的名称对应就会自动封装，pageable分页参数
        Page<UserInfo> users = esUserRepository.search(builder, pageable);

        //迭代器转list
        ArrayList<UserInfo> list = Lists.newArrayList(users);
        return ResponseEntity.ok();
    }


}