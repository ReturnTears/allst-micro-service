package com.allst.micro.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 封装分页请求参数
 *
 * @author Hutu
 * @since 2022-08-27 下午 09:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private String phone;
    private Integer userId;
    private Date startCreateTime;
    private Date endCreateTime;
}
