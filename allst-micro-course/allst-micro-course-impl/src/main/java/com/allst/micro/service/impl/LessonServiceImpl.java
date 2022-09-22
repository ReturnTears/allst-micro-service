package com.allst.micro.service.impl;

import com.allst.micro.entity.po.Lesson;
import com.allst.micro.enums.CourseLessonStatus;
import com.allst.micro.mapper.LessonMapper;
import com.allst.micro.service.ILessonService;
import com.allst.micro.service.IMediaService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-22 下午 09:52
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonMapper, Lesson> implements ILessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Autowired
    private IMediaService mediaService;

    @Override
    public Integer getReleaseCourse(Integer courseId) {
        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("status", CourseLessonStatus.RELEASE.getCode());
        queryWrapper.eq("is_del", Boolean.FALSE);

        return lessonMapper.selectCount(queryWrapper);
    }

    @Override
    public List<Lesson> getBySectionId(Integer sectionId) {
        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("section_id", sectionId);
        queryWrapper.eq("is_del", Boolean.FALSE);
        queryWrapper.orderByAsc("order_num");
        List<Lesson> lessons = lessonMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(lessons)) {
            return Collections.emptyList();
        }

        return lessons;
    }
}
