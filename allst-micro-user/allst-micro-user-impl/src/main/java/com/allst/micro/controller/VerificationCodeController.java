package com.allst.micro.controller;

import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IVerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@RestController
@RequestMapping("/user/vfcode")
public class VerificationCodeController {
    @Autowired
    IVerificationCodeService verificationCodeService;

    /**
     * 发送验证码
     */
    @RequestMapping("sendCode")
    public ResponseDTO<?> sendCode(String telephone) {
        boolean res = verificationCodeService.save(telephone);
        if (res) {
            return ResponseDTO.success();
        } else {
            return ResponseDTO.ofError("");
        }
    }

    /**
     * 判断验证码是否正确
     */
    @RequestMapping("checkCode")
    public ResponseDTO<?> checkCode(String telephone, String code) {
        ResponseDTO<?> responseDTO;
        try {
            boolean checkCode = verificationCodeService.checkCode(telephone, code);
            responseDTO = ResponseDTO.success(checkCode);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = ResponseDTO.ofError(e.getMessage());
        }
        return responseDTO;
    }

}
