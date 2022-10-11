package com.allst.micro.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hutu
 * @since 2022-10-11 下午 07:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrderRecord implements Serializable {
    private static final long serialVersionUID = 2885827689318298300L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;//主键
    private String orderNo;//订单号
    private String type;//操作类型CREATE|PAY|REFUND...
    private String fromStatus;//原订单状态
    private String toStatus;//新订单状态
    private Integer paidAmount;//实付金额，单位为分
    private String remark;//备注
    private String createdBy;//操作人
    private Date createdAt;//操作时间
}
