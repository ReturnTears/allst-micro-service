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
    /**
     * id
     */
    private Integer id;
    /**
     * 订单号 唯一
     */
    private String orderNo;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 课程id，根据订单中的课程类型来选择
     */
    private Integer courseId;
    /**
     * 订单来源类型: 1 用户下单购买 2 后台添加专栏
     */
    private Integer sourceType;
    /**
     * 当前状态: 0已创建 10已支付 20已完成 30已取消 40已过期
     */
    private Integer status;
    /**
     * 是否删除
     */
    private Integer isDel;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date update_time;
    /**
     * 活动课程id
     */
    private Integer activityCourseId;
}
