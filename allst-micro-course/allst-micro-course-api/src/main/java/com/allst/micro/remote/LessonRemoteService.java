package com.allst.micro.remote;

import com.allst.micro.dto.LessonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:57
 */
@FeignClient(name = "allst-micro-course", path = "/course/lesson")
public interface LessonRemoteService {
    /**
     * 保存或者更新课程
     */
    @PostMapping(value = "/saveOrUpdate", consumes = "application/json")
    boolean saveOrUpdate(@RequestBody LessonDTO lessonDTO);

    /**
     * 通过lessonId获取课时
     */
    @GetMapping(value = "/getById")
    LessonDTO getById(@RequestParam("lessonId") Integer lessonId);

    /**
     * 通过lessonId获取对应课时名称，map
     */
    @GetMapping(value = "/getByIds")
    Map<Integer, String> getByIds(@RequestParam("lessonIds") List<Integer> lessonIds);
}
