package com.allst.micro.entity;

import com.allst.micro.web.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色
 *
 * @author Hutu
 * @since 2022-08-28 下午 10:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Role extends BasePo {
    private String code;
    private String name;
    private String description;
}
