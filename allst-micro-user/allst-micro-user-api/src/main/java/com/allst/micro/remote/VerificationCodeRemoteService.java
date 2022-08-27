package com.allst.micro.remote;

import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 验证码远程调用服务类
 *
 * @author Hutu
 * @since 2022-08-27 下午 05:49
 */
@FeignClient(name = "allst-micro-user", path = "/user/verCode")
public interface VerificationCodeRemoteService {

    /**
     * 给指定手机发生验证码
     *
     * @param telephone 手机号
     *
     * @return 结果
     */
    @RequestMapping("sendCode")
    ResponseDTO<?> sendCode(@RequestParam("telephone") String telephone);

    /**
     * 判断验证码是否正确
     *
     * @param telephone 手机号
     * @param code      验证码
     *
     * @return 结果
     */
    @RequestMapping("checkCode")
    ResponseDTO<?> checkCode(@RequestParam("telephone") String telephone, @RequestParam("code") String code);
}
