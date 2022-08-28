package com.allst.micro.service.impl;

import com.allst.micro.entity.Role;
import com.allst.micro.provider.OrganizationProvider;
import com.allst.micro.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 角色 Service 实现类
 *
 * @author Hutu
 * @since 2022-08-28 下午 10:28
 */
@Service
public class RoleServiceImpl implements IRoleService {

    /**
     * 通过将 @Qualifier 注解与我们想要使用的特定 Spring bean 的名称一起进行装配，
     * Spring 框架就能从多个相同类型并满足装配要求的 bean 中找到我们想要的，避免让Spring脑裂。
     */
    @Qualifier("allst-micro-boot")
    @Autowired
    OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
