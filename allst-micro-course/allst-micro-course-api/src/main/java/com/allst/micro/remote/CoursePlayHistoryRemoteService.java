package com.allst.micro.remote;

import com.allst.micro.dto.CoursePlayHistoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:53
 */
@FeignClient(name = "allst-micro-course", path = "/course/coursePlayHistory")
public interface CoursePlayHistoryRemoteService {
    /**
     * 保存播放历史
     */
    @PostMapping(value = "/saveCourse", consumes = "application/json")
    void saveCourseHistoryNode(@RequestBody CoursePlayHistoryDTO playHistoryDTO);


    /**
     * 获取播放的课程
     */
    @GetMapping(value = "/hasStudyLessons", consumes = "application/json")
    List<?> hasStudyLessons(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId);

    /**
     * 获取课程播放节点
     */
    @GetMapping(value = "/getByLessonId", consumes = "application/json")
    CoursePlayHistoryDTO getByLessonId(@RequestParam("lessonId") Integer lessonId, @RequestParam("userId") Integer userId);
}
