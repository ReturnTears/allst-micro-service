package com.allst.micro.remote;

import com.allst.micro.dto.WeixinDTO;
import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户绑定微信远程调用服务类
 *
 * @author Hutu
 * @since 2022-08-27 下午 05:48
 */
@FeignClient(name = "allst-micro-user", path = "/user/weixin")
public interface WeixinRemoteService {
    /**
     * 通过用户id获取用户微信
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    @GetMapping("/getUserWeixinByUserId")
    WeixinDTO getUserWeixinByUserId(@RequestParam("userId") Integer userId);

    /**
     * 通过openid获取用户微信
     *
     * @param openId 微信认证用户唯一id
     *
     * @return 结果
     */
    @GetMapping("/getUserWeixinByOpenId")
    WeixinDTO getUserWeixinByOpenId(@RequestParam("openId") String openId);

    /**
     * 通过unionId获取用户微信
     *
     * @param unionId unionId
     *
     * @return 结果
     */
    @GetMapping("/getUserWeixinByUnionId")
    WeixinDTO getUserWeixinByUnionId(@RequestParam("unionId") String unionId);

    /**
     * 保存用户微信
     *
     * @param weixinDTO 参数
     *
     * @return 结果
     */
    @PostMapping("/saveUserWeixin")
    WeixinDTO saveUserWeixin(@RequestBody WeixinDTO weixinDTO);

    /**
     * 更新用户微信
     *
     * @param weixinDTO 参数
     *
     * @return 结果
     */
    @PostMapping("/updateUserWeixin")
    boolean updateUserWeixin(@RequestBody WeixinDTO weixinDTO);

    /**
     * 绑定用户微信
     *
     * @param weixinDTO 参数
     *
     * @return 结果
     */
    @PostMapping("/bindUserWeixin")
    ResponseDTO<WeixinDTO> bindUserWeixin(@RequestBody WeixinDTO weixinDTO);

    /**
     * 解绑用户微信
     *
     * @param userId 用户id
     *
     * @return 结果
     */
    @PostMapping("/unBindUserWeixin")
    boolean unBindUserWeixin(@RequestParam("userId") Integer userId);
}
