package com.allst.micro.job;

import com.allst.micro.service.ICourseService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hutu
 * @since 2022-09-22 下午 11:26
 */
@Component
public class CourseAutoOnlineJob {

    @Autowired
    ICourseService courseService;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("courseAutoOnlineJobHandler")
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobHelper.log("Execute course auto online.");
        courseService.courseAutoOnline();
        return ReturnT.SUCCESS;
    }
}
