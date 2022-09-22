package com.allst.micro.remote;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.allst.micro.constant.CacheDefine;
import com.allst.micro.date.DateUtil;
import com.allst.micro.dto.*;
import com.allst.micro.entity.po.Course;
import com.allst.micro.entity.po.Lesson;
import com.allst.micro.entity.po.Section;
import com.allst.micro.entity.po.Teacher;
import com.allst.micro.enums.CourseLessonStatus;
import com.allst.micro.enums.CourseStatus;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.*;
import com.allst.micro.util.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Hutu
 * @since 2022-09-22 下午 10:01
 */
@Service
@Slf4j
public class CourseService {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ILessonService lessonService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private CoursePlayHistoryService coursePlayHistoryService;

    @Autowired
    private UserCourseOrderRemoteService userCourseOrderRemoteService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private IActivityCourseService activityCourseService;

    //显示课时数
    private static final int SHOW_LESSON_NUM = 2;

    public List<CourseDTO> getAllCourses(Integer userId) {
        log.info("getAllCourses - userId:{}", userId);
        // 获取有效的课程
        List<Course> courses = getInvalidCourses();
        if (CollectionUtils.isEmpty(courses)) {
            log.info("[选课列表] 获取选课列表信息为空");
            return Collections.emptyList();
        }
        Map<Integer, UserCourseOrderDTO> orderMap = getUserCourseOrderMapForCourseList(userId);
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO();
            BeanUtils.copyProperties(course, courseDTO);
            courseDTOS.add(courseDTO);
        }
        //未购上新课程
        List<CourseDTO> newCourseList = new LinkedList<>();
        //未购课程
        List<CourseDTO> notPayCourseList = new LinkedList<>();
        //已购课程
        List<CourseDTO> payedCourseList = new LinkedList<>();
        for (CourseDTO courseDTO : courseDTOS) {
            Integer courseId = courseDTO.getId();
            // 设置购买标识
            setBuyFlag(orderMap, courseDTO, courseId);
            // 设置老师的信息
            setTeacher(courseDTO);
            // 设置topN课时
            setTopNCourseLesson(courseDTO);
            //保存上新且未购买的课程
            if (!courseDTO.getIsBuy() && org.apache.commons.lang.StringUtils.isNotBlank(courseDTO.getDiscountsTag())) {
                hasActivityCourse(courseDTO);
                newCourseList.add(courseDTO);
                continue;
            }
            //未购买课程
            if (!courseDTO.getIsBuy()) {
                hasActivityCourse(courseDTO);
                notPayCourseList.add(courseDTO);
                continue;
            }
            //已购买的课程列表
            payedCourseList.add(courseDTO);

        }

        //遍历组装courseList信息
        List<CourseDTO> courseList = new LinkedList<CourseDTO>();
        courseList.addAll(newCourseList);
        courseList.addAll(notPayCourseList);
        courseList.addAll(payedCourseList);
        return courseList;
    }

    /**
     *
     */
    private void hasActivityCourse(CourseDTO courseDTO) {
        String activityCourseStr = redisTemplate.opsForValue().get(CacheDefine.ActivityCourse.getKey(courseDTO.getId()));
        log.info("hasActivityCourse - activityCourseStr:{} courseId:{}", activityCourseStr, courseDTO.getId());
        if (null == activityCourseStr) {
            return;
        }
        ActivityCourseDTO activityCourseCache = JSON.parseObject(activityCourseStr, ActivityCourseDTO.class);
        if (!DateUtil.isBefore(new Date(), activityCourseCache.getBeginTime())) {
            return;
        }
        String stock = redisTemplate.opsForValue().get(CacheDefine.ActivityCourse.getStockKey(courseDTO.getId()));
        if (null == stock || Long.parseLong(stock) <= 0) {
            return;
        }
        long time = DateUtil.getMillisecond(new Date(), activityCourseCache.getEndTime());
        if (time <= 0) {
            return;
        }
        courseDTO.setActivityCourse(true);
        courseDTO.setDiscounts(activityCourseCache.getAmount());
        courseDTO.setActivityTime(time);
    }


    public Map<Integer, UserCourseOrderDTO> getUserCourseOrderMapForCourseList(Integer userId) {
        if (userId == null) {
            return Collections.emptyMap();
        }
        ResponseDTO<List<UserCourseOrderDTO>> orderResult = userCourseOrderRemoteService.getUserCourseOrderByUserId(userId);
        List<UserCourseOrderDTO> userOrders = orderResult.getContent();
        if (CollectionUtils.isEmpty(userOrders)) {
            return Collections.emptyMap();
        }
        return userOrders.stream().collect(Collectors.toMap(UserCourseOrderDTO::getCourseId, Function.identity()));
    }


    /**
     * 获取有效课程
     */
    private List<Course> getCourseByIds(List<Integer> courseIds) {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("status", CourseStatus.PUTAWAY.getCode());
        courseQueryWrapper.eq("is_del", Boolean.FALSE);
        courseQueryWrapper.in("id", courseIds);
        courseQueryWrapper.orderByDesc(" create_time ");

        return this.courseService.list(courseQueryWrapper);
    }

    public List<CourseDTO> getPurchasedCourse(Integer userId) {
        if (userId == null) {
            return Collections.emptyList();
        }
        // 1、 获取订单信息
        ResponseDTO<List<UserCourseOrderDTO>> ordersResult = userCourseOrderRemoteService.getUserCourseOrderByUserId(userId);
        List<UserCourseOrderDTO> userCourseOrderDTOS = ordersResult.getContent();
        if (CollectionUtils.isEmpty(userCourseOrderDTOS)) {
            log.info("[获取已购课程]  用户课程订单不存在，userId:{}", userId);
            return Lists.newArrayList();
        }
        //通过订单获取购买的课程Ids
        List<Integer> courseIds = userCourseOrderDTOS.stream().map(UserCourseOrderDTO::getCourseId).collect(Collectors.toList());
        List<Course> coursesList = getCourseByIds(courseIds);
        if (CollectionUtils.isEmpty(coursesList)) {
            return Collections.emptyList();
        }
        //获取已播放和未播放的课程
        List<CourseDTO> finalResultList = new ArrayList<>();
        for (Course course : coursesList) {
            Integer courseId = course.getId();
            CourseDTO courseDTO = new CourseDTO();
            CoursePlayHistoryDTO recordLearn = coursePlayHistoryService.getRecordLearn(courseId, userId);
            if (recordLearn != null) {
                Integer lessonId = recordLearn.getLessonId();
                Lesson lesson = lessonService.getById(lessonId);
                if (lesson != null) {
                    courseDTO.setLastLearnLessonName(lesson.getTheme());
                }
            }
            BeanUtils.copyProperties(course, courseDTO);
            // 设置课程更新数量
            setLessonUpdateCount(courseId, courseDTO);
            // 设置课程的最近播放时间
            setCourseLastPlayTimeAndLessonName(userId, course.getId(), courseDTO);

            //根据支付时间和播放时间，重设待排序比较时间
            resetCompareDate(courseDTO, userCourseOrderDTOS);
            finalResultList.add(courseDTO);
        }

        //按播放时间排序
        sortByCompareTime(finalResultList);
        return finalResultList;
    }

    //重设待排序时间
    private void resetCompareDate(CourseDTO course, List<UserCourseOrderDTO> userCourseOrderDTOS) {
        Map<Integer, UserCourseOrderDTO> orderMap = userCourseOrderDTOS.stream().collect(Collectors.toMap(UserCourseOrderDTO::getCourseId, Function.identity()));
        //不存在购买时间，直接返回
        UserCourseOrderDTO userOrder = orderMap.get(course.getId());
        if (userOrder == null || userOrder.getCreateTime() == null) {
            if (course.getCompareTime() == null) {
                course.setCompareTime(new Date(0));
            }
            return;
        }

        //当前面未设置播放时间时，将支付时间设置为待比较时间
        Date payTime = userOrder.getCreateTime();
        if (course.getCompareTime() == null) {
            course.setCompareTime(payTime);
            return;
        }

        //当播放时间和支付时间都不为空时，比较这两个时间，选出最新的时间
        if (payTime.compareTo(course.getCompareTime()) <= 0) {
            return;
        }
        course.setCompareTime(payTime);
    }

    /**
     * 按播放时间进行排序
     */
    private static void sortByCompareTime(List<CourseDTO> courseDTOList) {
        if (CollectionUtils.isEmpty(courseDTOList) || courseDTOList.size() <= 1) {
            return;
        }

        courseDTOList.sort((o1, o2) -> {
            Date o1Time = o1.getCompareTime();
            Date o2Time = o2.getCompareTime();

            if (o1Time == null && o2Time == null) {
                return 0;
            }

            if (o2Time == null) {
                return -1;
            }

            if (o1Time == null) {
                return 1;
            }
            return o2Time.compareTo(o1Time);
        });
    }

    /**
     * 设置课程更新数量
     */
    private void setLessonUpdateCount(Integer courseId, CourseDTO courseDTO) {
        Integer releaseCourse = lessonService.getReleaseCourse(courseId);
        courseDTO.setLessonUpdateCount(releaseCourse);
    }

    /**
     * 设置课程的最近播放时间
     */
    private void setCourseLastPlayTimeAndLessonName(Integer userId, Integer courseId, CourseDTO courseDTO) {
        CoursePlayHistoryDTO coursePlayHistory = coursePlayHistoryService.getByUserIdAndCourseId(userId, courseId);
        if (coursePlayHistory == null) {
            return;
        }
        courseDTO.setCompareTime(DateUtils.asDate(coursePlayHistory.getUpdateTime()));
        Lesson lesson = lessonService.getById(coursePlayHistory.getLessonId());
        courseDTO.setLastLearnLessonName(lesson == null ? null : lesson.getTheme());
    }

    /**
     * 获取topN课程
     */
    private void setTopNCourseLesson(CourseDTO courseDTO) {
        QueryWrapper lessonQueryWrapper = new QueryWrapper<>();
        lessonQueryWrapper.ne("status", CourseLessonStatus.HIDE.getCode());
        lessonQueryWrapper.eq("course_id", courseDTO.getId());
        lessonQueryWrapper.eq("is_del", Boolean.FALSE);
        lessonQueryWrapper.orderByAsc("section_id", "order_num");
        lessonQueryWrapper.last("limit 0 , " + SHOW_LESSON_NUM);
        List<LessonDTO> courseLessonDTOS = lessonService.list(lessonQueryWrapper);
        courseDTO.setTopNCourseLesson(courseLessonDTOS);
    }

    /**
     * 获取有效课程
     */
    private List<Course> getInvalidCourses() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("status", CourseStatus.PUTAWAY.getCode());
        courseQueryWrapper.eq("is_del", Boolean.FALSE);
        courseQueryWrapper.orderByDesc(" sort_num ");

        return this.courseService.list(courseQueryWrapper);
    }


    private void setTeacher(CourseDTO courseDTO) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseDTO.getId());
        queryWrapper.eq("is_del", Boolean.FALSE);
        List<Teacher> teachers = teacherService.list(queryWrapper);
        if (CollectionUtils.isEmpty(teachers)) {
            return;
        }
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teachers.get(0), teacherDTO);
        courseDTO.setTeacherDTO(teacherDTO);
    }

    /**
     * 设置购买标签
     */
    private void setBuyFlag(Map<Integer, UserCourseOrderDTO> orderMap, CourseDTO courseDTO, Integer courseId) {
        if (orderMap == null) {
            log.info("用户没有购买课程，订单为空");
            return;
        }
        UserCourseOrderDTO order = orderMap.get(courseId);
        if (order != null) {
            courseDTO.setIsBuy(Boolean.TRUE);
        }
    }


    private List<SectionDTO> getSectionDTOS(QueryWrapper<Lesson> lessonQueryWrapper, Course course, List<Section> sections) {
        List<SectionDTO> sectionDTOS = new ArrayList<SectionDTO>();
        //查询模块对应的课程
        for (Section section : sections) {
            SectionDTO sectionDTO = new SectionDTO();
            BeanUtil.copyProperties(section, sectionDTO);
            List<LessonDTO> lessonDTOS = new ArrayList<LessonDTO>();
            lessonQueryWrapper.clear();
            lessonQueryWrapper.eq("course_id", course.getId());
            lessonQueryWrapper.eq("section_id", section.getId());
            lessonQueryWrapper.eq("is_del", Boolean.FALSE);
            List<Lesson> lessons = lessonService.list(lessonQueryWrapper);
            for (Lesson lesson : lessons) {
                LessonDTO lessonDTO = new LessonDTO();
                BeanUtil.copyProperties(lesson, lessonDTO);
                lessonDTOS.add(lessonDTO);
            }

            sectionDTO.setLessonDTOS(lessonDTOS);
            sectionDTOS.add(sectionDTO);
        }
        return sectionDTOS;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveCourse(@RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        BeanUtil.copyProperties(courseDTO, course);
        course.setUpdateTime(LocalDateTime.now());
        course.setCreateTime(LocalDateTime.now());
        log.info("保存的课程信息：{}", JSON.toJSONString(course));
        boolean res = this.courseService.saveOrUpdate(course);
        TeacherDTO teacherDTO = courseDTO.getTeacherDTO();
        if (teacherDTO == null) {
            return Boolean.TRUE;
        }
        Teacher teacher = new Teacher();
        BeanUtil.copyProperties(teacherDTO, teacher);
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(LocalDateTime.now());
        teacher.setCreateTime(LocalDateTime.now());
        log.info("保存老师的信息为 teacher:{}", JSON.toJSONString(teacher));
        teacherService.saveOrUpdate(teacher);
        return res;
    }

}
