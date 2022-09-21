package com.allst.micro.remote;

import com.allst.micro.dto.SectionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:57
 */
@FeignClient(name = "allst-micro-course", path = "/course/section")
public interface SectionRemoteService {
    /**
     * 保存课程
     */
    @PostMapping(value = "/saveOrUpdateSection", consumes = "application/json")
    boolean saveOrUpdateSection(@RequestBody SectionDTO sectionDTO);


    /**
     * 通过课程Id获取章节和课时
     */
    @GetMapping(value = "/getSectionAndLesson")
    List<SectionDTO> getSectionAndLesson(@RequestParam("courseId") Integer courseId);


    /**
     * 获取章节信息
     */
    @GetMapping(value = "/getBySectionId", consumes = "application/json")
    SectionDTO getBySectionId(@RequestParam("sectionId") Integer sectionId);
}
