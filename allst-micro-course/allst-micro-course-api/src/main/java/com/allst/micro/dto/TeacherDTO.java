package com.allst.micro.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TeacherDTO implements Serializable {
    private Integer id;
    /**
     * 课程Id
     */
    private Integer courseId;

    /**
     * 讲师姓名
     */
    private String teacherName;

    /**
     * 讲师头像url地址
     */
    private String teacherHeadPicUrl;

    /**
     * 讲师职位
     */
    private String position;

    /**
     * 讲师描述
     */
    private String description;
}
