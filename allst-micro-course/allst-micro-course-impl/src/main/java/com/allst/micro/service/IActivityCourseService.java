package com.allst.micro.service;

import com.allst.micro.dto.ActivityCourseDTO;
import com.allst.micro.entity.po.ActivityCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:52
 */
public interface IActivityCourseService extends IService<ActivityCourse> {
    /**
     * 保存课程
     */
    void saveActivityCourse(ActivityCourseDTO reqDTO);

    /**
     * 保存或更新课程
     */
    void saveOrUpdateActivityCourse(ActivityCourseDTO reqDTO);

    /**
     * 更新课程状态
     */
    boolean updateActivityCourseStatus(ActivityCourseDTO reqDTO);

    /**
     * 通过课程id获取课程
     */
    ActivityCourseDTO getByCourseId(Integer courseId);

    /**
     * 更新课程stock
     */
    void updateActivityCourseStock(Integer courseId,String orderNo);
}
