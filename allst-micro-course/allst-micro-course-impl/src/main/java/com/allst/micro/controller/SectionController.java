package com.allst.micro.controller;

import com.allst.micro.dto.SectionDTO;
import com.allst.micro.remote.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:10
 */
@RestController
@RequestMapping("/course/section")
public class SectionController {

    @Autowired
    SectionService sectionService;

    /**
     * 保存课程
     */
    @PostMapping(value = "/saveOrUpdateSection")
    boolean saveOrUpdateSection(@RequestBody SectionDTO sectionDTO) {
        return sectionService.saveOrUpdateSection(sectionDTO);
    }


    /**
     * 通过课程Id获取章节和课时
     */
    @GetMapping(value = "/getSectionAndLesson")
    List<SectionDTO> getSectionAndLesson(@RequestParam("courseId") Integer courseId) {
        return sectionService.getSectionAndLesson(courseId);
    }


    /**
     * 获取章节信息
     */
    @GetMapping(value = "/getBySectionId")
    SectionDTO getBySectionId(@RequestParam("sectionId") Integer sectionId) {
        return sectionService.getBySectionId(sectionId);
    }
}
