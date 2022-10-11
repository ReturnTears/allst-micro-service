package com.allst.micro.model;

import lombok.Builder;
import lombok.Data;

/**
 * 阿里支付中的扩展内容，目前用于信贷信息的扩展
 *
 * @author Hutu
 * @since 2022-10-11 下午 10:04
 */
@Data
@Builder
public class AliBizExtendContent {
    // 花呗分期的期数
    private String hb_fq_num;
    // 花呗手续费的承担方
    private String hb_fq_seller_percent;
}
