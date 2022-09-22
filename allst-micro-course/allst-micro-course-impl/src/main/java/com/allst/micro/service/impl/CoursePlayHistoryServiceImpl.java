package com.allst.micro.service.impl;

import com.alibaba.fastjson.JSON;
import com.allst.micro.dto.CoursePlayHistoryDTO;
import com.allst.micro.entity.po.CoursePlayHistory;
import com.allst.micro.entity.po.Lesson;
import com.allst.micro.mapper.CoursePlayHistoryMapper;
import com.allst.micro.service.ICoursePlayHistoryService;
import com.allst.micro.service.ILessonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Hutu
 * @since 2022-09-22 下午 09:44
 */
@Slf4j
@Service
public class CoursePlayHistoryServiceImpl extends ServiceImpl<CoursePlayHistoryMapper, CoursePlayHistory> implements ICoursePlayHistoryService {

    @Autowired
    private CoursePlayHistoryMapper coursePlayHistoryMapper;

    @Autowired
    private ILessonService lessonService;

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    public CoursePlayHistory getByUserIdAndCourseId(Integer userId, Integer courseId) {
        QueryWrapper<CoursePlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("lesson_id", courseId);
        queryWrapper.orderByDesc("update_time");
        List<CoursePlayHistory> coursePlayHistories = coursePlayHistoryMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(coursePlayHistories)) {
            return null;
        }

        return coursePlayHistories.get(0);
    }

    @Override
    public void saveCourseHistoryNode(CoursePlayHistoryDTO playHistoryDTO) {
        checkParams(playHistoryDTO);
        // 待更新或保存的历史记录信息
        executorService.execute(new CoursePlayHistoryTask(playHistoryDTO));
    }

    // 开启一个保存播放记录的任务
    public class CoursePlayHistoryTask implements Runnable {
        private final CoursePlayHistoryDTO playHistory;

        public CoursePlayHistoryTask(CoursePlayHistoryDTO playHistory) {
            this.playHistory = playHistory;
        }

        @Override
        public void run() {
            if (playHistory == null) {
                return;
            }
            // 待更新或保存的历史记录信息
            recordCoursePlayHistory(playHistory);
        }
    }

    /**
     * 保存用户播放记录
     */
    private void recordCoursePlayHistory(CoursePlayHistoryDTO playHistory) {
        // 先检查上报课程信息的合法性
        if (!checkValidCourse(playHistory)) {
            return;
        }
        CoursePlayHistory historyRecord = new CoursePlayHistory();
        historyRecord.setHistoryNode(playHistory.getHistoryNode());

        Integer userId = playHistory.getUserId();
        Integer lessonId = playHistory.getLessonId();
        Integer node = playHistory.getHistoryNode();


        QueryWrapper<CoursePlayHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("lesson_id", lessonId);
        queryWrapper.orderByDesc("update_time");
        List<CoursePlayHistory> coursePlayHistories = coursePlayHistoryMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(coursePlayHistories)) {
            BeanUtils.copyProperties(playHistory, historyRecord);
            //兼容老逻辑，
            if (historyRecord.getSectionId() == null) {
                Lesson lesson = lessonService.getById(lessonId);
                historyRecord.setSectionId(lesson.getSectionId());
            }
            historyRecord.setHistoryHighestNode(node);
            historyRecord.setCreateTime(LocalDateTime.now());
            historyRecord.setUpdateTime(LocalDateTime.now());
            coursePlayHistoryMapper.insert(historyRecord);
            return;
        }
        CoursePlayHistory coursePlayHistory = coursePlayHistories.get(0);
        Integer historyId = coursePlayHistory.getId();
        Integer historyHighestNode = coursePlayHistory.getHistoryHighestNode();
        Integer oldHistoryNode = coursePlayHistory.getHistoryNode();
        updateCoursePlayHistory(historyId, node, historyRecord, historyHighestNode, oldHistoryNode);
    }

    /**
     * 更新课程播放记录
     *
     * @param historyId             历史记录主键ID
     * @param node                  当前播放点
     * @param historyRecord         待更新的历史记录信息
     * @param oldHistoryHighestNode 之前的最高播放记录
     */
    private void updateCoursePlayHistory(Integer historyId, Integer node, CoursePlayHistory historyRecord, Integer oldHistoryHighestNode, Integer oldHistoryNode) {

        //兼容加history_highest_node字段前的数据
        if (oldHistoryHighestNode == null) {
            oldHistoryHighestNode = oldHistoryNode;
        }

        //设置最高播放记录
        if (oldHistoryHighestNode < node) {
            historyRecord.setHistoryHighestNode(node);
        } else {
            historyRecord.setHistoryHighestNode(oldHistoryHighestNode);
        }

        historyRecord.setUpdateTime(LocalDateTime.now());
        historyRecord.setId(historyId);
        try {
            coursePlayHistoryMapper.updateById(historyRecord);
        } catch (Exception e) {
            log.error("更新播放记录失败,coursePlayHistory={}", historyRecord, e);
        }
    }

    // 检查有效课程
    private boolean checkValidCourse(CoursePlayHistoryDTO playHistory) {
        Lesson lesson = lessonService.getById(playHistory.getLessonId());
        if (lesson == null) {
            log.info("lessonId:{}对应的课时不存在,playHistory:{}", playHistory.getLessonId(), JSON.toJSONString(playHistory));
            return false;
        }

        // 判断lessonId对应的课程ID和章节ID是否正确
        if (Objects.equals(lesson.getCourseId(), playHistory.getCourseId())
                && Objects.equals(lesson.getSectionId(), playHistory.getSectionId())) {
            return true;
        }

        log.info("上报的课程信息不匹配，上报监控,playHistory:{}", JSON.toJSONString(playHistory));
        return false;
    }

    /**
     * 检查参数是否有效
     */
    private void checkParams(CoursePlayHistoryDTO playHistory) {
        if (playHistory == null) {
            log.info("playHistory为空");
            return;
        }

        if (playHistory.getUserId() == null) {
            log.info("playHistory.getLagouUserId为空");
            return;
        }

        if (playHistory.getCourseId() == null) {
            log.info("playHistory.getCourseId为空");
            return;
        }

        if (playHistory.getLessonId() == null) {
            log.info("playHistory.getLessonId为空");
            return;
        }

        if (playHistory.getHistoryNode() == null) {
            log.info("playHistory.getHistoryNode为空");
        }
    }
}
