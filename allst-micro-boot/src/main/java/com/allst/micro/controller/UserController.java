package com.allst.micro.controller;

import com.allst.micro.remote.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理前端控制器
 *
 * @author Hutu
 * @since 2022-08-27 下午 09:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRemoteService remoteService;

    
}
