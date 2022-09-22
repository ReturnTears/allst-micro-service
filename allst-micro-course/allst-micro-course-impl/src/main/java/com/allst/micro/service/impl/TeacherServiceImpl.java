package com.allst.micro.service.impl;

import com.allst.micro.entity.po.Teacher;
import com.allst.micro.mapper.TeacherMapper;
import com.allst.micro.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Hutu
 * @since 2022-09-22 下午 09:58
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

}
