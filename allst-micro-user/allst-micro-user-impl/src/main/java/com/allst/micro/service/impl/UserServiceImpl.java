package com.allst.micro.service.impl;

import com.allst.micro.entity.User;
import com.allst.micro.mapper.UserMapper;
import com.allst.micro.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
