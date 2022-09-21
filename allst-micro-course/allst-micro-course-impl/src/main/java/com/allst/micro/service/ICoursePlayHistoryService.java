package com.allst.micro.service;

import com.allst.micro.dto.CoursePlayHistoryDTO;
import com.allst.micro.entity.po.CoursePlayHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:55
 */
public interface ICoursePlayHistoryService extends IService<CoursePlayHistory> {

    CoursePlayHistory getByUserIdAndCourseId(Integer userId, Integer courseId);

    void saveCourseHistoryNode(CoursePlayHistoryDTO playHistoryDTO);

}
