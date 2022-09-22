package com.allst.micro.controller;

import com.allst.micro.dto.CourseDTO;
import com.allst.micro.remote.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:00
 */
@Slf4j
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;


    /**
     * 获取选课列表
     */
    @GetMapping("/getAllCourses")
    public List<CourseDTO> getAllCourses(@RequestParam(required = false, name = "userId") Integer userId) {
        return courseService.getAllCourses(userId);
    }


    /**
     * 获取已购课程信息
     */
    @GetMapping("/getPurchasedCourse")
    List<CourseDTO> getPurchasedCourse(@RequestParam("userId") Integer userId) {
        return courseService.getPurchasedCourse(userId);
    }

}
