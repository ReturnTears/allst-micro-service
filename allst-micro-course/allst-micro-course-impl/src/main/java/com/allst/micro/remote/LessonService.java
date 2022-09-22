package com.allst.micro.remote;

import com.allst.micro.dto.LessonDTO;
import com.allst.micro.entity.po.Lesson;
import com.allst.micro.service.ILessonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:44
 */
@Service
public class LessonService {

    @Autowired
    ILessonService lessonService;

    public boolean saveOrUpdate(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(lessonDTO, lesson);
        if (lesson.getId() == null) {
            lesson.setCreateTime(LocalDateTime.now());
        }
        lesson.setUpdateTime(LocalDateTime.now());
        return lessonService.saveOrUpdate(lesson);
    }

    public LessonDTO getById(Integer lessonId) {
        Lesson lesson = lessonService.getById(lessonId);
        if (lesson == null) {
            return null;
        }
        LessonDTO lessonDTO = new LessonDTO();
        BeanUtils.copyProperties(lesson, lessonDTO);
        return lessonDTO;
    }

    public Map<Integer, String> getByIds(List<Integer> lessonIds) {

        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", lessonIds);
        List<Lesson> lessons = lessonService.list(queryWrapper);
        if (CollectionUtils.isEmpty(lessons)) {
            return Maps.newHashMap();
        }
        return lessons.stream().collect(Collectors.toMap(Lesson::getCourseId, Lesson::getTheme));
    }
}
