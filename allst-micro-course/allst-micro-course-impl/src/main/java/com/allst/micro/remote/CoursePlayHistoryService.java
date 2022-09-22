package com.allst.micro.remote;

import com.allst.micro.dto.CoursePlayHistoryDTO;
import com.allst.micro.entity.po.CoursePlayHistory;
import com.allst.micro.service.ICoursePlayHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:03
 */
@Service
public class CoursePlayHistoryService {

    @Autowired
    private ICoursePlayHistoryService coursePlayHistoryService;

    public void saveCourseHistoryNode(CoursePlayHistoryDTO playHistoryDTO) {
        coursePlayHistoryService.saveCourseHistoryNode(playHistoryDTO);
    }

    public List<Integer> hasStudyLessons(Integer lagouUserId, Integer courseId) {
        QueryWrapper<CoursePlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", lagouUserId);
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("is_del", Boolean.FALSE);
        List<CoursePlayHistory> coursePlayHistorys = coursePlayHistoryService.list(queryWrapper);
        if (CollectionUtils.isEmpty(coursePlayHistorys)) {
            return Collections.emptyList();
        }
        return coursePlayHistorys.stream()
                .map(CoursePlayHistory::getLessonId)
                .collect(Collectors.toList());
    }

    public CoursePlayHistoryDTO getByLessonId(Integer lessonId, Integer userId) {
        QueryWrapper<CoursePlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lesson_id", lessonId);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("is_del", Boolean.FALSE);
        List<CoursePlayHistory> courserPlayHistorys = coursePlayHistoryService.list(queryWrapper);
        if (CollectionUtils.isEmpty(courserPlayHistorys)) {
            return null;
        }
        CoursePlayHistory coursePlayHistory = courserPlayHistorys.get(0);
        CoursePlayHistoryDTO coursePlayHistoryDTO = new CoursePlayHistoryDTO();
        BeanUtils.copyProperties(coursePlayHistory, coursePlayHistoryDTO);
        return coursePlayHistoryDTO;
    }

    public CoursePlayHistoryDTO getRecordLearn(Integer courseId, Integer userId) {
        QueryWrapper<CoursePlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("is_del", Boolean.FALSE);
        queryWrapper.orderByDesc("create_time");
        List<CoursePlayHistory> courserPlayHistorys = coursePlayHistoryService.list(queryWrapper);
        if (CollectionUtils.isEmpty(courserPlayHistorys)) {
            return null;
        }
        CoursePlayHistory coursePlayHistory = courserPlayHistorys.get(0);
        CoursePlayHistoryDTO coursePlayHistoryDTO = new CoursePlayHistoryDTO();
        BeanUtils.copyProperties(coursePlayHistory, coursePlayHistoryDTO);
        return coursePlayHistoryDTO;
    }

    public CoursePlayHistoryDTO getByUserIdAndCourseId(Integer userId, Integer courseId) {
        CoursePlayHistory coursePlayHistory = coursePlayHistoryService.getByUserIdAndCourseId(userId, courseId);
        if (coursePlayHistory == null) {
            return null;
        }
        CoursePlayHistoryDTO coursePlayHistoryDTO = new CoursePlayHistoryDTO();
        BeanUtils.copyProperties(coursePlayHistory, coursePlayHistoryDTO);
        return coursePlayHistoryDTO;
    }

}
