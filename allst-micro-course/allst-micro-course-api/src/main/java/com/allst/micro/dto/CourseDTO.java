package com.allst.micro.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CourseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程一句话简介
     */
    private String brief;

    /**
     * 课程描述
     */
    private String courseDescription;

    /**
     * 讲师id
     */
    private Integer teacherId;

    /**
     * 课时数
     */
    private Integer totalCourseTime;

    /**
     * 显示销量
     */
    private Integer sales;

    /**
     * 真实销量
     */
    private Integer actualSales;

    /**
     * 原价
     */
    private Double price;

    /**
     * 原价标签
     */
    private String priceTag;

    /**
     * 优惠价
     */
    private Double discounts;

    /**
     * 优惠标签
     */
    private String discountsTag;


    /**
     * 课程分享图片url
     */
    private String courseImgUrl;
    /**
     * 分享title
     */
    private String shareTitle;

    /**
     * 分享描述
     */
    private String shareDescription;

    /**
     * 分享图title
     */
    private String shareImageTitle;

    /**
     * 是否新品
     */
    private Boolean isNew;

    /**
     * 广告语
     */
    private String isNewDes;

    /**
     * 最后操作者
     */
    private Integer lastOperatorId;

    /**
     * 记录创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 自动上架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime autoOnlineTime;

    /**
     * 是否删除
     */
    private Boolean isDel;

    /**
     * 总时长(分钟)
     */
    private Integer totalDuration;

    /**
     * seo描述
     */
    private String seoDescription;

    /**
     * seo关键词
     */
    private String seoKeywords;

    /**
     * html的title
     */
    private String seoTitle;


    /**
     * h5课程跳转页面（需产品定URL）
     */
    private String h5Url;

    /**
     * 课程列表展示图片
     */
    private String courseListImg;

    /**
     * APP端开悟言职列表的课程标记
     */
    private String tag;

    /**
     * 课程状态，0-草稿，1-上架
     */
    private Integer status;

    /**
     * 课程排序，用于后台保存草稿时用到
     */
    private Integer sortNum;

    /**
     * 佣金比例
     */
    private Integer brokerageRate;

    /**
     * 是否参与分销
     */
    private Boolean joinDistribution;

    /**
     * 最后课程最近通知时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastNoticeTime;

    /**
     * 课程预览第一个字段
     */
    private String previewFirstField;

    /**
     * 课程预览第二个字段
     */
    private String previewSecondField;

    /**
     * 分销海报图片URL
     */
    private String distributionPosterImage;

    /**
     * 分销文案l列表
     */
    private String distributionCopywriter;

    /**
     * 是否是灰度课程
     */
    private Boolean isGray;

    /**
     * 课程中关模块
     */
    private List<SectionDTO> sectionDTOS;


    /**
     * 讲师
     */
    private TeacherDTO teacherDTO;


    /**
     * 播放地址
     */
    private String courseUrl;
    /**
     * 课时列表
     */
    private List<LessonDTO> topNCourseLesson;
    /**
     * 是否购买
     */
    private Boolean isBuy = false;

    /**
     * 课程更新数量
     */
    private Integer lessonUpdateCount;
    /**
     * 待比较时间  最近播放时间
     */
    private Date compareTime;
    /**
     * 最后学习的课时名称
     */
    private String lastLearnLessonName;
    /**
     * 课程描述的markdown
     */
    private String courseDescriptionMarkDown;

    /**
     * 课程是否做秒杀活动 true是 false不是
     */
    private Boolean activityCourse = false;
    /**
     * 倒计时 课程活动倒计时 单位毫秒
     */
    private Long activityTime;

    /**
     * 活动课程信息
     */
    private ActivityCourseDTO activityCourseDTO;
}
