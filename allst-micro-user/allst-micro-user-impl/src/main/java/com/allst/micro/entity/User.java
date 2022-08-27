package com.allst.micro.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户实体
 * </p>
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户头像地址
     */
    private String portrait;

    /**
     * 注册手机
     */
    private String phone;

    /**
     * 用户密码（可以为空，支持只用验证码注册、登录）
     */
    private String password;

    /**
     * 注册ip
     */
    private String regIp;

    /**
     * 是否有效用户
     */
    private Boolean accountNonExpired;

    /**
     * 账号是否未过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 是否未锁定
     */
    private Boolean accountNonLocked;

    /**
     * 用户状态：ENABLE能登录，DISABLE不能登录
     */
    private String status;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 注册时间
     */
    private Date createTime;

    /**
     * 记录更新时间
     */
    private Date updateTime;


}
