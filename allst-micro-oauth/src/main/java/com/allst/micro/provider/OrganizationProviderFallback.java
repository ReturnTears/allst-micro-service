package com.allst.micro.provider;

import com.allst.micro.entity.Role;
import com.allst.micro.entity.vo.Result;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Hutu
 * @since 2022-08-28 下午 10:30
 */
@Component
public class OrganizationProviderFallback implements OrganizationProvider {
    @Override
    public Result<Set<Role>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<Role>());
    }
}
