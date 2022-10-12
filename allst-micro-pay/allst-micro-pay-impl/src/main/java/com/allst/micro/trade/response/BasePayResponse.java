package com.allst.micro.trade.response;

import com.allst.micro.model.OutTrade;
import lombok.Data;

import java.io.Serializable;

/**
 * 支付基础响应对象
 *
 * @author Hutu
 * @since 2022-10-12 下午 09:07
 */
@Data
public class BasePayResponse implements Serializable {
    private static final long serialVersionUID = 942497671183248456L;

    public boolean isSuccess() {
        return true;
    }

    private String url;
    private OutTrade trade;
    private String resStr;
}
