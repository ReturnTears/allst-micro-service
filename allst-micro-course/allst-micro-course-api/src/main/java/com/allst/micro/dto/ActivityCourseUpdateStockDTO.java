package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-09-21 下午 08:29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ActivityCourseUpdateStockDTO implements Serializable {
    private Integer id;
}
