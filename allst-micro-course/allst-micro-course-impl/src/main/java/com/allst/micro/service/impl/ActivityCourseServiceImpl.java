package com.allst.micro.service.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ConverUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.date.DateUtil;
import com.allst.micro.dto.ActivityCourseDTO;
import com.allst.micro.entity.po.ActivityCourse;
import com.allst.micro.mapper.ActivityCourseMapper;
import com.allst.micro.service.IActivityCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Hutu
 * @since 2022-09-21 下午 10:01
 */
@Slf4j
@Service
public class ActivityCourseServiceImpl extends ServiceImpl<ActivityCourseMapper, ActivityCourse> implements IActivityCourseService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ActivityCourseMapper activityCourseMapper;

    @Override
    public void saveActivityCourse(ActivityCourseDTO reqDTO) {
        log.info("saveActivityCourse - reqDTO:{}", JSON.toJSONString(reqDTO));
        checkParam(reqDTO);
        ActivityCourse activityCourse = ConverUtil.convert(reqDTO, ActivityCourse.class);
        assert activityCourse != null;
        activityCourse.setCreateTime(new Date());
        activityCourse.setCreateUser("auto");
        activityCourse.setUpdateTime(activityCourse.getCreateTime());
        activityCourse.setUpdateUser("auto");

        save(activityCourse);
    }

    @Override
    public void saveOrUpdateActivityCourse(ActivityCourseDTO reqDTO) {

    }

    @Override
    public boolean updateActivityCourseStatus(ActivityCourseDTO reqDTO) {
        return false;
    }

    @Override
    public ActivityCourseDTO getByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public void updateActivityCourseStock(Integer courseId, String orderNo) {

    }

    private void checkParam(ActivityCourseDTO reqDTO) {
        ValidateUtils.notNullParam(reqDTO);
        ValidateUtils.notNullParam(reqDTO.getAmount());
        ValidateUtils.isTrue(reqDTO.getAmount() > 0, "价格必须大于零");
        ValidateUtils.notNullParam(reqDTO.getCourseId());
        ValidateUtils.isTrue(reqDTO.getCourseId() > 0, "课程必须大于零");
        ValidateUtils.notNullParam(reqDTO.getStock());
        ValidateUtils.isTrue(reqDTO.getStock() > 0, "库存必须大于零");
        ValidateUtils.notNullParam(reqDTO.getBeginTime());
        ValidateUtils.notNullParam(reqDTO.getEndTime());
        ValidateUtils.isTrue(DateUtil.isBefore(reqDTO.getEndTime(), reqDTO.getBeginTime()), "结束时间必须大于开始时间");
    }
}
