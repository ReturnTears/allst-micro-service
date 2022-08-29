package com.allst.micro.service.impl;

import com.allst.micro.dto.UserDTO;
import com.allst.micro.remote.UserRemoteService;
import com.allst.micro.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户接口实现类
 *
 * @author Hutu
 * @since 2022-08-29 下午 10:23
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRemoteService userRemoteService;

    @Cacheable(value = "#phone")
    @Override
    public UserDTO getByPhone(String phone) {
        return this.userRemoteService.getUserByPhone(phone);
    }

    @Cacheable(value = "#userId")
    @Override
    public UserDTO getByUserId(Integer userId) {
        return this.userRemoteService.getUserById(userId);
    }

    @Override
    public UserDTO save(String name, String phone, String portrait, String password) {
        UserDTO dto = new UserDTO();
        dto.setAccountNonExpired(true);
        dto.setAccountNonLocked(true);
        dto.setCredentialsNonExpired(true);
        dto.setStatus("ENABLE");
        dto.setRegIp("127.0.0.1");
        dto.setName(name);
        dto.setPassword(password);
        dto.setCreateTime(new Date());
        dto.setDel(false);
        dto.setPhone(phone);
        dto.setUpdateTime(new Date());
        dto.setPortrait(portrait);
        return this.userRemoteService.saveUser(dto);
    }
}
