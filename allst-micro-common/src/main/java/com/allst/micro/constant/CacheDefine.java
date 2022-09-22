package com.allst.micro.constant;

/**
 * 缓存key结构定义
 *
 * @author Hutu
 * @since 2022-09-22 下午 09:37
 */
public interface CacheDefine {
    String PREFIX = "edu";
    String SP = ":";
    String DOT = ".";
    String U = "_";

    interface ActivityCourse {
        static String getKey(Integer activityCourseId) {
            return PREFIX + SP + "activityCourse" + SP + activityCourseId;
        }

        static String getStockKey(Integer activityCourseId) {
            return PREFIX + SP + "activityCourse" + SP + "stock" + SP + activityCourseId;
        }
    }
}
