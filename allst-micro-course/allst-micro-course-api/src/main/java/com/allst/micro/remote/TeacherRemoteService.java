package com.allst.micro.remote;

import com.allst.micro.dto.TeacherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:58
 */
@FeignClient(name = "allst-micro-course", path = "/teacher")
public interface TeacherRemoteService {
    /**
     * 通过课程Id获取老师信息
     */
    @GetMapping(value = "/getTeacherByCourseId", consumes = "application/json")
    TeacherDTO getTeacherByCourseId(Integer courseId);
}
