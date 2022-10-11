package com.allst.micro.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author Hutu
 * @since 2022-10-11 下午 10:03
 */
@Data
@Builder
public class AliBizContent {
    private String out_trade_no;
    private String product_code;
    private String subject;
    private Double total_amount;

    private String quit_url;  //手机网站支付必须参数

    private String qr_pay_mode;
    private Integer qrcode_width;

    //退款参数
    private Double refund_amount;
    private String refund_reason;

    // 扩展信息
    private AliBizExtendContent extend_params;

    // 查询是用于查询支付渠道信息
    private String[] query_options;
}
