package com.allst.micro.service.impl;

import com.allst.micro.entity.UserCourseOrderRecord;
import com.allst.micro.mapper.UserCourseOrderRecordMapper;
import com.allst.micro.service.IUserCourseOrderRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 课程订单记录Service实现类
 *
 * @author Hutu
 * @since 2022-09-24 下午 11:01
 */
@Slf4j
@Service
public class UserCourseOrderRecordServiceImpl extends ServiceImpl<UserCourseOrderRecordMapper, UserCourseOrderRecord> implements IUserCourseOrderRecordService {
}
