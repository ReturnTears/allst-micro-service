package com.allst.micro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户绑定微信实体
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_weixin")
public class Weixin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 认证id,微信对应的时unionId
     */
    private String unionId;

    /**
     * openId
     */
    private String openId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别, 1-男，2-女
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Boolean isDel;


}
