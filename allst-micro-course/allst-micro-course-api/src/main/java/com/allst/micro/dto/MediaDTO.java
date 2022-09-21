package com.allst.micro.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MediaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
     * 媒体渠道，1-阿里云，2-腾讯云，3-七牛云
     */
    private Integer channel;

    /**
     * 媒体类型，0-音频，1-视频
     */
    private Integer mediaType;

    /**
     * 封面图URL
     */
    private String coverImageUrl;

    /**
     * 时长（06:02）
     */
    private String duration;

    /**
     * 媒体资源唯一标识
     */
    private String fileId;
    /**
     * 媒体资源url地址
     */
    private String fileUrl;

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
     * 媒体状态
     */
    private Integer status;

    /**
     * 是否删除，0未删除，1删除
     */
    private Boolean isDel;

    /**
     * 最后操作者ID
     */
    private Integer lastOperatorId;

    /**
     * 时长，秒数（主要用于音频在H5控件中使用）
     */
    private Integer durationNum;
}
