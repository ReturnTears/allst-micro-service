package com.allst.micro.model;

import com.allst.micro.enums.*;

import java.io.Serializable;

/**
 * 支付下单入参
 *
 * @author Hutu
 * @since 2022-10-11 下午 10:05
 */
public class CreateParams implements Serializable {
    private static final long serialVersionUID = 266753606807999292L;

    private String productName;//商品名称
    private String clientIp;
    private String returnUrl;//h5支付成功回调地址
    private String openId;//JSAPI微信公众号支付用户唯一标识

    // 支付平台，目前主要应用于app端，不同的平台对应着后面的支付商户数据不同
    private Platform platform;

    // 原先BaseParams中的字段
    // 不适用这里的原因在于原先productId为Integer类型，为了兼容老版本和新版本的支付逻辑，所以将productId更改为字符串类型
    // 因为只有api层会调用，不会存在dubbo内部调用的情况，所以这样改是安全的
    protected Integer userId;
    protected Double amount;//支付金额
    protected Currency currency;//币种
    protected Channel channel;//支付渠道
    protected Source source;//来源
    protected OrderType orderType;
    protected String productId;//商品标识
    protected Status status;
    protected Integer count;//购买数量

    public String getProductName() {
        return productName;
    }

    public CreateParams setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public CreateParams setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public CreateParams setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public CreateParams setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public CreateParams setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public CreateParams setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Currency getCurrency() {
        return currency;
    }

    public CreateParams setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public Channel getChannel() {
        return channel;
    }

    public CreateParams setChannel(Channel channel) {
        this.channel = channel;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public CreateParams setSource(Source source) {
        this.source = source;
        return this;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public CreateParams setOrderType(OrderType orderType) {
        this.orderType = orderType;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public CreateParams setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public CreateParams setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public CreateParams setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Platform getPlatform() {
        return platform;
    }

    public CreateParams setPlatform(Platform platform) {
        this.platform = platform;
        return this;
    }
}
