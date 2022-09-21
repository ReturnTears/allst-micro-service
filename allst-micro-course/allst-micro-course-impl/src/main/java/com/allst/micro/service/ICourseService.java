package com.allst.micro.service;

import com.allst.micro.entity.po.Course;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:56
 */
public interface ICourseService extends IService<Course> {

    IPage<Course> selectPage(Page<?> page, Wrapper<?> wrapper);

    /**
     * 根据配置的自动上架时间，定时任务扫描达到上架时间的草稿状态的课程进行上架。
     */
    void courseAutoOnline();
}
