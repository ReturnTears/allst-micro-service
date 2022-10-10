package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallBackRes implements Serializable {
    private static final long serialVersionUID = -1494134899283606056L;
    private String resStr;//返回结果
}
