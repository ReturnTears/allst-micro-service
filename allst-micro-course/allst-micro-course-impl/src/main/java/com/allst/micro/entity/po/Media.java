package com.allst.micro.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("course_media")
public class Media implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程媒体主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程Id
     */
    private Integer courseId;

    /**
     * 章ID
     */
    private Integer sectionId;

    /**
     * 课时ID
     */
    private Integer lessonId;

    /**
     * 封面图URL
     */
    private String coverImageUrl;

    /**
     * 时长（06:02）
     */
    private String duration;

    /**
     * 媒体资源文件对应的EDK
     */
    private String fileEdk;

    /**
     * 文件大小MB
     */
    private Double fileSize;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 媒体资源文件对应的DK
     */
    private String fileDk;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除，0未删除，1删除
     */
    private Boolean isDel;

    /**
     * 时长，秒数（主要用于音频在H5控件中使用）
     */
    private Integer durationNum;

    /**
     * 媒体资源文件ID
     */
    private String fileId;
}
