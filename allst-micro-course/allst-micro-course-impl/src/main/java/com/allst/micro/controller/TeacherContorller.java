package com.allst.micro.controller;

import com.allst.micro.dto.TeacherDTO;
import com.allst.micro.remote.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:10
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherContorller {

    @Autowired
    TeacherService teacherService;

    /**
     * 通过课程Id获取老师信息
     */
    @GetMapping(value = "/getTeacherByCourseId")
    TeacherDTO getTeacherByCourseId(Integer courseId) {
        return teacherService.getByCourseId(courseId);
    }
}
