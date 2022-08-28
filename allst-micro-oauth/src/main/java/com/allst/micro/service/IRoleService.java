package com.allst.micro.service;

import com.allst.micro.entity.Role;

import java.util.Set;

/**
 * 角色 Service
 *
 * @author Hutu
 * @since 2022-08-28 下午 10:26
 */
public interface IRoleService {
    /**
     * 通过用户id查询用户角色
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    Set<Role> queryUserRolesByUserId(String userId);
}
