package com.allst.micro.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品订单表
 *
 * @author Hutu
 * @since 2022-09-22 下午 10:25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCourseOrderDTO implements Serializable {
    private static final long serialVersionUID = -4920910964792305692L;
    private Integer id;//id
    private String orderNo;//订单号 唯一
    private Integer userId;//用户id
    private Integer courseId;//课程id，根据订单中的课程类型来选择
    private Integer sourceType;//订单来源类型: 1 用户下单购买 2 后台添加专栏
    private Integer status;//当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期
    private Integer isDel;//是否删除
    private Date createTime;//创建时间
    private Date update_time;//更新时间
    private Integer activityCourseId;//活动课程id
}
