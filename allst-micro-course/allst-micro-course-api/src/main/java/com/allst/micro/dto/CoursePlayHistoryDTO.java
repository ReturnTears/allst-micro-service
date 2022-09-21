package com.allst.micro.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoursePlayHistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 课程id
     */
    private Integer courseId;

    /**
     * 章节id
     */
    private Integer sectionId;

    /**
     * 课时id
     */
    private Integer lessonId;

    /**
     * 历史播放节点(s)
     */
    private Integer historyNode;

    /**
     * 最高历史播放节点
     */
    private Integer historyHighestNode;

    /**
     * 记录创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
