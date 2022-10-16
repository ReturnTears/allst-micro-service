package com.allst.micro.trade.impl;

import com.allst.micro.Util.ConverUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.trade.AbstractThirdPayServer;
import com.allst.micro.trade.ThirdPayServer;
import com.allst.micro.trade.ThirdPayService;
import com.allst.micro.trade.request.BasePayRequest;
import com.allst.micro.trade.request.aliPay.AliPayRequest;
import com.allst.micro.trade.request.wechatPay.WechatPayRequest;
import com.allst.micro.trade.response.BasePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 第三支付实现类
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:26
 */
@Slf4j
@Service
public class ThirdPayServiceImpl implements ThirdPayService {

    @Autowired
    Map<String, ThirdPayServer<?, ?>> thirdPayServerMap;

    @Override
    public BasePayResponse submitPay(BasePayRequest request) {
        ThirdPayServer thirdPayServer = thirdPayServerMap.get(StringUtils.join(request.getChannel(), AbstractThirdPayServer.PAY_SERVER));
        ValidateUtils.isTrue(null != thirdPayServer, "未找到对应的支付服务");
        if (thirdPayServer instanceof AliPayServer) {
            request = ConverUtil.convert(request, AliPayRequest.class);
        } else if (thirdPayServer instanceof WechatPayServer) {
            request = ConverUtil.convert(request, WechatPayRequest.class);
        }
        return thirdPayServer.submitPay(request);
    }

    @Override
    public BasePayResponse callBack(BasePayRequest request) {
        ThirdPayServer thirdPayServer = thirdPayServerMap.get(StringUtils.join(request.getChannel(), AbstractThirdPayServer.PAY_SERVER));
        ValidateUtils.isTrue(null != thirdPayServer, "未找到对应的支付服务");
        if (thirdPayServer instanceof AliPayServer) {
            request = ConverUtil.convert(request, AliPayRequest.class);
        } else if (thirdPayServer instanceof WechatPayServer) {
            request = ConverUtil.convert(request, WechatPayRequest.class);
        }
        return thirdPayServer.callBack(request);
    }
}
