package com.allst.micro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-10 下午 09:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallBackReq implements Serializable {

    private static final long serialVersionUID = 8689003597912687786L;

    private String channel;//支付渠道

    private String wxCallBackReqStr;//微信回调请求参数

    private Map<String, String> aliParams;//支付宝回调请求参数
}
