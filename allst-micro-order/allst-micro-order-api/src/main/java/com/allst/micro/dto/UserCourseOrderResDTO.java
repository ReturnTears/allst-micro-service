package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品订单保存返回对象
 *
 * @author Hutu
 * @since 2022-09-22 下午 10:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseOrderResDTO implements Serializable {
    private static final long serialVersionUID = 8581183520872075235L;

    private String orderNo;//订单号
}
