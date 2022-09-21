package com.allst.micro.mapper;

import com.allst.micro.entity.po.ActivityCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author Hutu
 * @since 2022-09-21 下午 09:35
 */
public interface ActivityCourseMapper extends BaseMapper<ActivityCourse> {
    @Update(" update activity_course set stock = stock - #{num} where id = #{id} and stock >= #{num} ")
    int updateStock(@Param("id") Integer id, @Param("num") Integer num);
}
