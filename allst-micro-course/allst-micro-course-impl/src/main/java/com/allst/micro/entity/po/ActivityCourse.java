package com.allst.micro.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityCourse implements Serializable {
    private static final long serialVersionUID = -1108922818880532217L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 活动开始时间
     */
    private Date beginTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动价格
     */
    private Double amount;
    /**
     * 库存值
     */
    private Integer stock;
    /**
     * 状态 0未上架 10已上架
     */
    private Integer status;
    /**
     * 逻辑删除 0未删除 1删除
     */
    private Integer isDel;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新人
     */
    private String updateUser;
}
