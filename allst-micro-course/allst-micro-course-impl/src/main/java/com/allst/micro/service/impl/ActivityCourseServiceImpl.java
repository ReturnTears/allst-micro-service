package com.allst.micro.service.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.Util.ConverUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.constant.CacheDefine;
import com.allst.micro.date.DateUtil;
import com.allst.micro.dto.ActivityCourseDTO;
import com.allst.micro.entity.po.ActivityCourse;
import com.allst.micro.enums.ActivityCourseStatus;
import com.allst.micro.mapper.ActivityCourseMapper;
import com.allst.micro.service.IActivityCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        log.info("saveOrUpdateActivityCourse - reqDTO:{}", JSON.toJSONString(reqDTO));
        checkParam(reqDTO);
        ActivityCourse activityCourse = ConverUtil.convert(reqDTO, ActivityCourse.class);
        assert activityCourse != null;
        activityCourse.setUpdateTime(activityCourse.getCreateTime());
        activityCourse.setUpdateUser("auto");

        ActivityCourseDTO activityCourseDTODB = getByCourseId(reqDTO.getCourseId());
        if (null == activityCourseDTODB) {
            activityCourse.setCreateTime(new Date());
            activityCourse.setCreateUser("auto");
            save(activityCourse);
        } else {
            activityCourse.setId(activityCourseDTODB.getId());
            ValidateUtils.isTrue(updateById(activityCourse), "活动课程更新失败，id:" + activityCourse.getId());
        }
        redisTemplate.opsForValue().set(CacheDefine.ActivityCourse.getKey(activityCourse.getCourseId()), JSON.toJSONString(ConverUtil.convert(activityCourse, ActivityCourseDTO.class)), DateUtil.getSecond(new Date(), activityCourse.getEndTime()), TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(CacheDefine.ActivityCourse.getStockKey(activityCourse.getCourseId()), activityCourse.getStock().toString(), DateUtil.getSecond(new Date(), activityCourse.getEndTime()), TimeUnit.SECONDS);
    }

    @Override
    public boolean updateActivityCourseStatus(ActivityCourseDTO reqDTO) {
        log.info("updateActivityCourseStatus - reqDTO:{}", JSON.toJSONString(reqDTO));
        ValidateUtils.notNullParam(reqDTO);
        ValidateUtils.notNullParam(reqDTO.getId());
        ValidateUtils.isTrue(reqDTO.getId() > 0, "活动课程id必须大于零");
        ValidateUtils.notNullParam(ActivityCourseStatus.parse(reqDTO.getStatus()));

        ActivityCourse activityCourseDB = getById(reqDTO.getId());
        ValidateUtils.notNullParam(activityCourseDB);

        if (activityCourseDB.getStatus().equals(reqDTO.getStatus())) {
            return true;
        }

        activityCourseDB.setStatus(reqDTO.getStatus());
        boolean res = updateById(activityCourseDB);
        ValidateUtils.isTrue(res, "更新状态失败");

        redisTemplate.opsForValue().set(CacheDefine.ActivityCourse.getKey(activityCourseDB.getCourseId()), JSON.toJSONString(ConverUtil.convert(activityCourseDB, ActivityCourseDTO.class)), DateUtil.getSecond(new Date(), activityCourseDB.getEndTime()), TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(CacheDefine.ActivityCourse.getStockKey(activityCourseDB.getCourseId()), activityCourseDB.getStock().toString(), DateUtil.getSecond(new Date(), activityCourseDB.getEndTime()), TimeUnit.SECONDS);

        return res;
    }

    @Override
    public ActivityCourseDTO getByCourseId(Integer courseId) {
        log.info("getByCourseId - courseId:{}", courseId);
        ValidateUtils.notNullParam(courseId);
        ValidateUtils.isTrue(courseId > 0, "课程id必须大于零");
        ActivityCourse activityCourseDB = getOne(new QueryWrapper<ActivityCourse>().eq("course_id", courseId));
        if (null == activityCourseDB) {
            return null;
        }
        return ConverUtil.convert(activityCourseDB, ActivityCourseDTO.class);
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
