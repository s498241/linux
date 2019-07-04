package com.controller;


import com.entity.UserInfo;
import com.service.UserInfoService;
import com.util.IdUtil;
import io.shardingsphere.transaction.api.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ss
 * @since 2019-06-18
 */
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("userInfo")
    @Transactional
    public boolean userInfo(UserInfo userInfo) {
        userInfo.setUserId(IdUtil.getId());
        boolean result = userInfoService.save(userInfo);
        return result;
    }

}

