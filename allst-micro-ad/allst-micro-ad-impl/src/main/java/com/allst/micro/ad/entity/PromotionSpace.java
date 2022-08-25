package com.allst.micro.ad.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hutu
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionSpace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 广告位key
     */
    @TableField("spaceKey")
    private String spaceKey;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 是否删除：0未删除 1已删除
     */
    @TableField("isDel")
    private Integer isDel;

}
