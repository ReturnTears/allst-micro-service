package com.allst.micro.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LessonDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 章节id
     */
    private Integer sectionId;

    /**
     * 课时主题
     */
    private String theme;

    /**
     * 课时时长 HH:ss
     */
    private String duration;
    /**
     * 课时时长
     */
    private Integer durationNum;
    /**
     * 是否免费
     */
    private Boolean isFree;

    /**
     * 课时背景图
     */
    private String startImgUrl;

    /**
     * 课时内容
     */
    private String textContent;

    /**
     * 课时内容（markdown文本）
     */
    private String markdownTextContent;

    /**
     * 转码集合
     */
    private String transcode;

    /**
     * 课程资源路径
     */
    private String resourceUrl;

    /**
     * 最后操作者id
     */
    private Integer lastOperatorId;

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

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 排序字段
     */
    private Integer orderNum;

    /**
     * 阿里云视频文件URL
     */
    private String aliFileUrl;

    /**
     * 阿里云视频文件的DK
     */
    private String aliFileDk;

    /**
     * 阿里云视频文件的EDK
     */
    private String aliFileEdk;

    /**
     * 阿里云视频资源文件ID
     */
    private String aliFileVid;

    /**
     * 课时状态,0-隐藏，1-未发布，2-已发布
     */
    private Integer status;

    /**
     * 是否定时发布
     */
    private Boolean isTimingPublish;

    /**
     * 定时发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 课程对应的视频信息
     */
    private MediaDTO mediaDTO;
}
