package com.allst.micro.dto;

import com.allst.micro.enums.UserCourseOrderSourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建商城商品订单的请求
 *
 * @author Hutu
 * @since 2022-09-22 下午 10:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateShopGoodsOrderReqDTO implements Serializable {
    private static final long serialVersionUID = 6507306131413105949L;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单来源
     */
    private UserCourseOrderSourceType sourceType;
}
