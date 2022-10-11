package com.allst.micro.model;

import com.allst.micro.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author Hutu
 * @since 2022-10-11 下午 10:07
 */
@Data
@Builder
public class OutTrade {
    private String orderNo;
    private String outTradeNo;
    private Status status;
    private String msg;
    private String buyId;
    private Date payTime;
    private Map<String, String> extra;
}
