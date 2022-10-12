package com.allst.micro.utils;

import com.allst.micro.enums.Platform;
import com.allst.micro.model.CreateParams;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:14
 */
public class OrderUtils {
    public static String OPENID = "openid";
    public static String RETURN_URL = "return_url";
    public static String PAY_URL = "pay_url";
    public static String PAY_LIST = "pay_list";

    // 提现时的终端信息
    public static String CASH_OUT_DEVICE_INFO = "device_info";
    public static String ERROR_MESSAGE = "error_message";

    /**
     * 订单的失效时长 单位小时
     */
    public static final int ORDER_EXPIRE_TIME_HOUR = 1;
    /**
     * 订单的失效时长 单位秒
     */
    public static final int ORDER_EXPIRE_TIME_SECOND = ORDER_EXPIRE_TIME_HOUR * 60 * 60;

    private static Gson gson = new Gson();

    public static void validCreateParams(CreateParams params) {
        if (params.getUserId() == null || params.getUserId() <= 0) {
            throw new IllegalArgumentException("用户id错误");
        }
        if (StringUtils.isEmpty(params.getClientIp())) {
            throw new IllegalArgumentException("用户ip错误");
        }
        if (params.getAmount() == null || params.getAmount() <= 0) {
            throw new IllegalArgumentException("金额为空");
        }
        if (params.getChannel() == null) {
            throw new IllegalArgumentException("支付渠道错误");
        }
        if (params.getCurrency() == null) {
            throw new IllegalArgumentException("币种错误");
        }
        if (params.getProductId() == null) {
            throw new IllegalArgumentException("商品标识为空");
        }
        if (params.getOrderType() == null) {
            throw new IllegalArgumentException("支付类型错误");
        }
    }

    /**
     * 获取平台或者使用默认的
     */
    public static Platform getPlatformOrDefault(Platform platform) {
        return platform == null ? Platform.LAGOU : platform;
    }
}
