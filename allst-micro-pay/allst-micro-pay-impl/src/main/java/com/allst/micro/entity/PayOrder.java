package com.allst.micro.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付订单实体
 *
 * @author Hutu
 * @since 2022-10-11 下午 07:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayOrder implements Serializable {
    private static final long serialVersionUID = 1541582061607L;

    private Long id;//主键
    private String orderNo;//订单号(唯一)
    private Integer userId;//用户ID
    private Integer productId;//商品唯一标识(ID)
    private String productName;//产品名称
    private BigDecimal amount;//金额,单位元
    private Integer count;//商品数量
    private String currency;//'货币类型，cny-人民币 gbeans-勾豆'
    private String channel;//'支付渠道：weChat-微信支付，aliPay-支付宝支付,applePay-苹果支付',
    private Integer status;//'订单状态：1-未支付 2-支付成功 3-支付失败 -1-订单失效',
    private Integer channelStatus;//'渠道中的状态码值'
    private Integer orderType;//类型 1-购买课程 2-充值
    private Integer source;//支付来源 1-app 2-h5 3-pc
    private String clientIp;//用户支付IP
    private String buyId;//购买账号id
    private String outTradeNo;//外部支付渠道交易号
    @TableField(value = "created_time", jdbcType = JdbcType.DATE)
    private Date createdTime;//创建时间
    @TableField(value = "updated_time", jdbcType = JdbcType.DATE)
    private Date updatedTime;//更新时间
    @TableField(value = "pay_time", jdbcType = JdbcType.DATE)
    private Date payTime;//支付时间
    private String extra;//'附加字段，KV json，例如:{"mobile":13020202,"success_url":123}'
    private String goodsOrderNo;//商品订单编号
    private Integer platform;//支付所使用的平台：1 拉勾 2 拉勾教育
    private Integer wxType;//'微信类型, 参考自lg-wechat-boot项目中的wxinfo',


    public void addExtra(String key, String value) {
        Map<String, String> extraMap = new HashMap<String, String>();
        extraMap.put(key, value);
        this.extra = JSON.toJSONString(extraMap);
    }

    public String getExtraElement(String key) {
        if (this.extra == null) {
            return null;
        }
        Map<String, String> extraMap = JSON.parseObject(this.extra, Map.class);
        return extraMap.get(key);
    }
}
