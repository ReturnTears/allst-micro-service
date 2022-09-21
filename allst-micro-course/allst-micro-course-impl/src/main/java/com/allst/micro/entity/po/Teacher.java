package com.allst.micro.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 讲师姓名
     */
    private String teacherName;

    /**
     * 职务
     */
    private String position;

    /**
     * 讲师介绍
     */
    private String description;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Boolean isDel;
}
