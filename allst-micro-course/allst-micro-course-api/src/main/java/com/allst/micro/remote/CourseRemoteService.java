package com.allst.micro.remote;

import com.allst.micro.dto.CourseDTO;
import com.allst.micro.dto.PageResultDTO;
import com.allst.micro.param.CourseQueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:55
 */
@FeignClient(name = "allst-micro-course", path = "/course")
public interface CourseRemoteService {
    /**
     * 获取选课列表
     */
    @GetMapping("/getAllCourses")
    List<CourseDTO> getAllCourses(@RequestParam(required = false, name = "userId") Integer userId);

    /**
     * 获取已购课程信息
     */
    @GetMapping("/getPurchasedCourse")
    List<CourseDTO> getPurchasedCourse(@RequestParam("userId") Integer userId);

    /**
     * 获取课程详情
     */
    @GetMapping("/getCourseById")
    CourseDTO getCourseById(@RequestParam("courseId") Integer courseId,@RequestParam("userId") Integer userId);


    /**
     * 更新课程
     */
    @PostMapping(value = "/saveOrUpdateCourse",consumes = "application/json")
    boolean saveOrUpdateCourse(@RequestBody CourseDTO courseDTO);

    /**
     * 获取课程
     */
    @PostMapping(value = "/getQueryCourses",consumes = "application/json")
    PageResultDTO<CourseDTO> getQueryCourses(@RequestBody CourseQueryParam courseQueryParam);
}
