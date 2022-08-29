package com.allst.micro.service;

import com.allst.micro.dto.UserDTO;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author Hutu
 * @since 2022-08-29 下午 10:19
 */
public interface IUserService {
    /**
     * 根据用户手机号获取用户信息
     */
    UserDTO getByPhone(String phone);

    /**
     * 根据用户唯一标识获取用户信息
     */
    UserDTO getByUserId(Integer userId);

    UserDTO save(String name, String phone, String portrait, String password);
}
