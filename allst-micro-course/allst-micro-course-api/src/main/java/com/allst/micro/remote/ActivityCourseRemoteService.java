package com.allst.micro.remote;

import com.allst.micro.dto.ActivityCourseDTO;
import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:45
 */
@FeignClient(name = "allst-micro-course", path = "/activityCourse")
public interface ActivityCourseRemoteService {
    /**
     * 保存活动课程
     */
    @PostMapping("/saveActivityCourse")
    ResponseDTO<?> saveActivityCourse(@RequestBody ActivityCourseDTO reqDTO);

    /**
     * 更新活动课程
     */
    @PostMapping("/updateActivityCourseStatus")
    ResponseDTO<?> updateActivityCourseStatus(@RequestBody ActivityCourseDTO reqDTO);

    /**
     * 通过id获取活动课程
     */
    @GetMapping("/getById")
    ResponseDTO<ActivityCourseDTO> getById(@RequestParam("id") Integer id);

    /**
     * 通过课程id获取活动课程
     */
    @GetMapping("/getByCourseId")
    ResponseDTO<ActivityCourseDTO> getByCourseId(@RequestParam("courseId") Integer courseId);

    /**
     * 更新活动课程
     */
    @PostMapping("/updateActivityCourseStock")
    ResponseDTO<?> updateActivityCourseStock(@RequestParam("courseId")Integer courseId,@RequestParam("orderNo")String orderNo);
}
