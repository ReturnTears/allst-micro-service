package com.allst.micro.controller;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ConverUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.dto.ActivityCourseDTO;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IActivityCourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hutu
 * @since 2022-09-21 下午 10:22
 */
@Slf4j
@RestController
@RequestMapping("/activityCourse")
public class ActivityCourseController {

    @Autowired
    IActivityCourseService activityCourseService;

    /**
     *
     */
    @PostMapping("/saveActivityCourse")
    public ResponseDTO<?> saveActivityCourse(@RequestBody ActivityCourseDTO reqDTO) {
        log.info("saveActivityCourse - reqDTO:{}", JSON.toJSONString(reqDTO));
        activityCourseService.saveActivityCourse(reqDTO);
        return ResponseDTO.success();
    }

    /**
     *
     */
    @PostMapping("/updateActivityCourseStatus")
    public ResponseDTO<?> updateActivityCourseStatus(@RequestBody ActivityCourseDTO reqDTO) {
        log.info("updateActivityCourseStatus - reqVo:{}", JSON.toJSONString(reqDTO));
        ValidateUtils.isTrue(activityCourseService.updateActivityCourseStatus(reqDTO), "更新状态失败");
        return ResponseDTO.success();
    }


    /**
     *
     */
    @GetMapping("/getById")
    ResponseDTO<ActivityCourseDTO> getById(@RequestParam("id") Integer id) {
        log.info("getById - id:{}", id);
        ValidateUtils.notNullParam(id);
        return ResponseDTO.success(ConverUtil.convert(activityCourseService.getById(id), ActivityCourseDTO.class));
    }

    /**
     *
     */
    @GetMapping("/getByCourseId")
    ResponseDTO<ActivityCourseDTO> getByCourseId(@RequestParam("courseId") Integer courseId) {
        log.info("getByCourseId - courseId:{}", courseId);
        ValidateUtils.notNullParam(courseId);
        return ResponseDTO.success(activityCourseService.getByCourseId(courseId));
    }

    /**
     *
     */
    @PostMapping("/updateActivityCourseStock")
    ResponseDTO<?> updateActivityCourseStock(@RequestParam("courseId") Integer courseId, @RequestParam("orderNo") String orderNo) {
        activityCourseService.updateActivityCourseStock(courseId, orderNo);
        return ResponseDTO.success();
    }
}
