package com.allst.micro.trade.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.StreamUtil;
import com.allst.micro.Util.ValidateUtils;
import com.allst.micro.config.AliPayMchConfig;
import com.allst.micro.date.DateUtil;
import com.allst.micro.entity.PayOrder;
import com.allst.micro.enums.Source;
import com.allst.micro.enums.Status;
import com.allst.micro.model.OutTrade;
import com.allst.micro.service.IPayOrderService;
import com.allst.micro.trade.AbstractThirdPayServer;
import com.allst.micro.trade.request.aliPay.AliPayRequest;
import com.allst.micro.trade.response.aliPay.AliPayResponse;
import com.allst.micro.utils.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 支付宝支付服务
 *
 * @author Hutu
 * @since 2022-10-16 下午 03:43
 */
@Slf4j
@Service("aliPay" + AbstractThirdPayServer.PAY_SERVER)
public class AliPayServer extends AbstractThirdPayServer<AliPayRequest, AliPayResponse> {

    @Autowired
    IPayOrderService orderService;

    protected Signature priKeySign;
    protected Signature pubKeySign;

    @Autowired
    AliPayMchConfig aliPayMchConfig;

    private final Gson gson = new Gson();

    @PostConstruct
    public void initKey() {
        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = StreamUtil.readText(new ByteArrayInputStream(aliPayMchConfig.getPrivateKey().getBytes())).getBytes();
            encodedKey = Base64.decodeBase64(encodedKey);
            PrivateKey privateKey = factory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
            priKeySign = Signature.getInstance(AlipayConstants.SIGN_SHA256RSA_ALGORITHMS);
            priKeySign.initSign(privateKey);

            StringWriter writer = new StringWriter();
            StreamUtil.io(new InputStreamReader(new ByteArrayInputStream(aliPayMchConfig.getPublicKey().getBytes())), writer);
            encodedKey = writer.toString().getBytes();
            encodedKey = Base64.decodeBase64(encodedKey);
            PublicKey publicKey = factory.generatePublic(new X509EncodedKeySpec(encodedKey));
            pubKeySign = Signature.getInstance(AlipayConstants.SIGN_SHA256RSA_ALGORITHMS);
            pubKeySign.initVerify(publicKey);
        } catch (Exception e) {
            log.error("initKey - err:", e);
            ValidateUtils.isTrue(false, "支付宝初始化配置失败");
        }
    }

    @Override
    public AliPayResponse submitPay(AliPayRequest request) {
        AliPayResponse res = new AliPayResponse();
        PayOrder order = request.getOrder();
        String url = order.getExtraElement(OrderUtils.PAY_URL);
        if (StringUtils.isNotEmpty(url)) {
            res.setUrl(url);
            return res;
        }

        ValidateUtils.isTrue(request.getSource() == Source.PC, "目前值支持PC支付");
        Map<String, String> params = AliPayUtil.newAliPCParams(aliPayMchConfig, order);
        SignUtils.addRsa256Sign(priKeySign, params);
        try {
            log.info("AliPay - submitPay request params:{}", JSON.toJSONString(params));
            HttpEntity httpEntity = HttpUtils.get(String.format("%s?%s", aliPayMchConfig.getPayHost(), UrlUtils.encode(params)));
            log.info("AliPay - submitPay response httpEntity:{}", JSON.toJSONString(httpEntity));
            if (httpEntity != null) {
                Map<String, Object> resp = parseResponse(httpEntity);
                log.info("AliPay - submitPay response resp:{}", JSON.toJSONString(resp));
                url = Objects.toString(resp.get("qr_code"));
                res.setUrl(url);
            }
        } catch (IOException e) {
            log.error("支付宝创建订单失败,orderNod：{}", order.getOrderNo(), e);
            ValidateUtils.isTrue(false, "支付宝创建订单失败");
        }

        if (url != null) {
            order.addExtra(OrderUtils.PAY_URL, url);
            PayOrder updateOrder = new PayOrder();
            updateOrder.setId(order.getId());
            updateOrder.setExtra(order.getExtra());
            orderService.updateById(updateOrder);
        }
        return res;
    }

    @Override
    public AliPayResponse callBack(AliPayRequest request) {
        Map<String, String> paramMap = request.getParams();
        /**
         * 基本校验
         */
        boolean checkV1 = SignUtils.verifyRsa256Sign(pubKeySign, paramMap);
        ValidateUtils.isTrue(checkV1, "支付宝通知数据基本验签不通过");

        String tradeStatus = paramMap.get("trade_status");
        final Status status = tradeStatus.equals("TRADE_SUCCESS") || tradeStatus.equals("TRADE_FINISHED") ? Status.PAY_SUCCESS : Status.NOT_PAY;
        OutTrade trade = null;
        AliPayResponse response = new AliPayResponse();
        try {
            trade = OutTrade.builder()
                    .status(status)
                    .outTradeNo(paramMap.get("trade_no"))
                    .orderNo(paramMap.get("out_trade_no"))
                    .buyId(paramMap.get("buyer_id"))
                    .payTime(status == Status.PAY_SUCCESS ? DateUtil.string2Date(paramMap.get("gmt_payment")) : null)
                    .build();
        } catch (ParseException e) {
            log.error("callBack - request:{} - err:", JSON.toJSONString(request), e);
        }

        response.setTrade(trade);
        response.setResStr(status == Status.PAY_SUCCESS ? "success" : null);
        return response;
    }

    private Map<String, Object> parseResponse(HttpEntity entity) throws IOException {
        StringWriter stringWriter = new StringWriter();
        IOUtils.copy(entity.getContent(), stringWriter);
        String resp = stringWriter.toString();
        int index = StringUtils.indexOf(resp, ":") + 1;
        int lastIndex = StringUtils.lastIndexOf(resp, "\"");
        resp = StringUtils.substring(resp, index, lastIndex);
        index = StringUtils.lastIndexOf(resp, "}");
        lastIndex = StringUtils.lastIndexOf(resp, "\"");

        String sign = StringUtils.substring(resp, lastIndex + 1);
        String content = StringUtils.substring(resp, 0, index + 1);
        Map<String, String> params = new HashMap<>();
        params.put("sign", sign);
        params.put("content", content);

        boolean flag = SignUtils.verifyRsa256Sign(pubKeySign, sign, content);
        if (!flag) {
            return Collections.EMPTY_MAP;
        }
        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(content, type);
    }

}
