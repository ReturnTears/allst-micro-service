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
 *  广告实体
 * </p>
 *
 * @author Hutu
 * @since 2022-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionAd implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 广告名
     */
    private String name;

    /**
     * 广告位id
     */
    @TableField("spaceId")
    private Integer spaceId;

    /**
     * 精确搜索关键词
     */
    private String keyword;

    /**
     * 静态广告的内容
     */
    @TableField("htmlContent")
    private String htmlContent;

    /**
     * 文字一
     */
    private String text;

    /**
     * 链接一
     */
    private String link;

    /**
     * 开始时间
     */
    @TableField("startTime")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("endTime")
    private Date endTime;

    /**
     * 创建日期
     */
    @TableField("createTime")
    private Date createTime;

    /**
     * 更新日期
     */
    @TableField("updateTime")
    private Date updateTime;

    /**
     * 状态：0无效 1有效
     */
    private Integer status;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 广告缩略图
     */
    private String img;


}
