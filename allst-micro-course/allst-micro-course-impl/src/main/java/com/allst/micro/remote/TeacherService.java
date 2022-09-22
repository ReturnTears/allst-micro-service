package com.allst.micro.remote;

import cn.hutool.core.bean.BeanUtil;
import com.allst.micro.dto.TeacherDTO;
import com.allst.micro.entity.po.Teacher;
import com.allst.micro.service.ITeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:03
 */
@Slf4j
@Service
public class TeacherService {

    @Autowired
    ITeacherService teacherService;

    public TeacherDTO getByCourseId(Integer courseId) {
        log.info("通过课程ID获取老师信息 courseId:{}", courseId);
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("is_del", Boolean.FALSE);
        List<Teacher> teachers = teacherService.list(queryWrapper);
        if (CollectionUtils.isEmpty(teachers)) {
            return null;
        }
        Teacher teacher = teachers.get(0);
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtil.copyProperties(teacherDTO, teacher);
        return teacherDTO;
    }
}
