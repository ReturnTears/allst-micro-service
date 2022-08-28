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


    @Qualifier("allst-micro-boot")
    @Autowired
    OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }
}
