package com.allst.micro.provider;

import com.allst.micro.entity.Role;
import com.allst.micro.entity.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @author Hutu
 * @since 2022-08-28 下午 10:30
 */
@FeignClient(name = "allst-micro-boot", fallback = OrganizationProviderFallback.class)
public interface OrganizationProvider {
    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 接口
     */
    @GetMapping(value = "/role/user/{userId}")
    Result<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);
}
