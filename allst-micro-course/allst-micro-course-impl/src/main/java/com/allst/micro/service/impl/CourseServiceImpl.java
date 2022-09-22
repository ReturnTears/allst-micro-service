package com.allst.micro.service.impl;

import com.allst.micro.entity.po.Course;
import com.allst.micro.mapper.CourseMapper;
import com.allst.micro.service.ICourseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author Hutu
 * @since 2022-09-22 下午 09:49
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    public IPage<Course> selectPage(Page<Course> page, Wrapper<Course> wrapper) {
        return courseMapper.selectPage(page, wrapper);
    }

    @Override
    @Transactional
    public void courseAutoOnline() {
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", 1).set("update_time", LocalDateTime.now());
        updateWrapper.eq("status", 0).isNotNull("auto_online_time").le("auto_online_time", LocalDateTime.now());
        this.update(updateWrapper);
    }
}
