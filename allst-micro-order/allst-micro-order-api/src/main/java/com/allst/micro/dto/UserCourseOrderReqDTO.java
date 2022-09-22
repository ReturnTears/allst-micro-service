package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品订单保存请求参数
 *
 * @author Hutu
 * @since 2022-09-22 下午 10:26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCourseOrderReqDTO implements Serializable {
    private static final long serialVersionUID = -4196012864441742122L;
    private Integer userId;//用户id
    private Integer type;//订单中的课程类型: 1 专栏 2 作者伴读 3 就业课 4 视频课 5 训练营
    private Integer courseId;//课程id，根据订单中的课程类型来选择
    private Integer sourceType;//订单来源类型: 1 用户下单购买 2 后台添加专栏 3 购买就业课(结算商城订单) 4 后台录入训练营学员 5 企业版录入学员
}
