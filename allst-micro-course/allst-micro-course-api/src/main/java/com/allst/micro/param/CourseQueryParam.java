package com.allst.micro.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseQueryParam implements Serializable {
    Integer currentPage;
    Integer pageSize;
    String courseName;
    Integer status;
}
