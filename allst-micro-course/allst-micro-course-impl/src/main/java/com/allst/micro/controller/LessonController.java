package com.allst.micro.controller;

import com.allst.micro.dto.LessonDTO;
import com.allst.micro.remote.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:08
 */
@RestController
@RequestMapping("/course/lesson")
public class LessonController {
    @Autowired
    LessonService lessonService;


    @GetMapping(value = "/getByIds")
    Map<Integer, String> getByIds(@RequestParam("lessonIds") List<Integer> lessonIds) {
        return lessonService.getByIds(lessonIds);
    }

    /**
     * 保存或者更新课程
     */
    @PostMapping(value = "/saveOrUpdate")
    boolean saveOrUpdate(@RequestBody LessonDTO lessonDTO) {
        return lessonService.saveOrUpdate(lessonDTO);
    }

    /**
     * 通过lessonId获取课时
     */
    @GetMapping(value = "/getById")
    LessonDTO getById(@RequestParam("lessonId") Integer lessonId) {
        return lessonService.getById(lessonId);
    }

}
