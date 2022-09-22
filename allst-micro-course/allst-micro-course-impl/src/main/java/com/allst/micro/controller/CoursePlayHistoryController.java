package com.allst.micro.controller;

import com.alibaba.fastjson.JSON;
import com.allst.micro.dto.CoursePlayHistoryDTO;
import com.allst.micro.remote.CoursePlayHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:06
 */
@Slf4j
@RestController
@RequestMapping("/course/coursePlayHistory")
public class CoursePlayHistoryController {

    @Autowired
    CoursePlayHistoryService coursePlayHistoryService;

    /**
     * 保存播放历史
     */
    @PostMapping(value = "/saveCourse", consumes = "application/json")
    void saveCourseHistoryNode(@RequestBody CoursePlayHistoryDTO playHistoryDTO) {
        log.info("保存历史节点 playHistoryDTO:{}", JSON.toJSONString(playHistoryDTO));
        coursePlayHistoryService.saveCourseHistoryNode(playHistoryDTO);
    }

    /**
     * 获取播放的课程
     */
    @GetMapping(value = "/hasStudyLessons")
    List<Integer> hasStudyLessons(@RequestParam("userId") Integer userId, @RequestParam("courseId") Integer courseId) {
        return coursePlayHistoryService.hasStudyLessons(userId, courseId);
    }

    /**
     * 获取课程播放节点
     */
    @GetMapping(value = "/getByLessonId", consumes = "application/json")
    CoursePlayHistoryDTO getByLessonId(@RequestParam("lessonId") Integer lessonId, @RequestParam("userId") Integer userId) {
        return coursePlayHistoryService.getByLessonId(lessonId, userId);
    }
}
