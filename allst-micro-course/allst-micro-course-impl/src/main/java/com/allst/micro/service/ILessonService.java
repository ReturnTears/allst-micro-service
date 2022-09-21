package com.allst.micro.service;

import com.allst.micro.entity.po.Lesson;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:58
 */
public interface ILessonService extends IService<Lesson> {

    Integer getReleaseCourse(Integer courseId);

    List<Lesson> getBySectionId(Integer sectionId);

}
