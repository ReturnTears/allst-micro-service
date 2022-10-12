package com.allst.micro.utils;

import com.allst.micro.config.AliPayMchConfig;
import com.allst.micro.date.DateUtil;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.model.AliBizContent;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Hutu
 * @since 2022-10-12 下午 08:07
 */
public class AliPayUtil {

    private static Gson gson = new Gson();

    public static Map<String, String> newAliBaseParams(AliPayMchConfig aliPayMchConfig) {
        Map<String, String> params = new TreeMap<>();
        params.put("app_id", aliPayMchConfig.getAppId());
        params.put("charset", "UTF-8");
        params.put("format", "json");
        params.put("sign_type", aliPayMchConfig.getSignType());
        params.put("timestamp", DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
        params.put("version", "1.0");
        params.put("notify_url", aliPayMchConfig.getNotifyUrl());
        return params;
    }

    public static Map<String, String> newAliPCParams(AliPayMchConfig aliPayMchConfig, PayOrder order) {
        Map<String, String> params = newAliBaseParams(aliPayMchConfig);
        AliBizContent bizContent = newAliContent(order, aliPayMchConfig.getUseFakeMoney());
        bizContent.setProduct_code("FACE_TO_FACE_PAYMENT");
        bizContent.setQr_pay_mode("4");
        bizContent.setQrcode_width(130);
        params.put("method", "alipay.trade.precreate");
        Object returnUrl = order.getExtraElement(OrderUtils.RETURN_URL);
        if (returnUrl != null) {
            params.put(OrderUtils.RETURN_URL, String.valueOf(returnUrl));
        }
        params.put("biz_content", gson.toJson(bizContent));
        return params;
    }

    public static Map<String, String> newAliQueryParams(AliPayMchConfig aliPayMchConfig, String orderNo) {
        Map<String, String> params = newAliBaseParams(aliPayMchConfig);
        AliBizContent bizContent = AliBizContent.builder().out_trade_no(orderNo).query_options(new String[]{"FUND_BILL_LIST", "TRADE_SETTLE_INFO"}).build();
        params.put("method", "alipay.trade.query");
        params.put("biz_content", gson.toJson(bizContent));

        return params;
    }


    private static AliBizContent newAliContent(PayOrder order, Boolean useFakeMoney) {
        AliBizContent bizContent = null;
        if (useFakeMoney) {
            bizContent = AliBizContent.builder().out_trade_no(order.getOrderNo()).subject(order.getProductName())
                    .total_amount(0.01).build();
        } else {
            bizContent = AliBizContent.builder().out_trade_no(order.getOrderNo()).subject(order.getProductName())
                    .total_amount((order.getAmount().multiply(new BigDecimal(order.getCount()))).doubleValue()).build();
        }
        return bizContent;
    }
}
