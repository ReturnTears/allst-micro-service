package com.allst.micro.remote;

import com.allst.micro.exception.ExpireCodeRuntimeException;
import com.allst.micro.exception.IncorrectCodeRuntimteException;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IVerificationCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码远程调用服务实现类
 *
 * @author Hutu
 * @since 2022-08-27 下午 06:25
 */
@Slf4j
@RestController
@RequestMapping("/user/verCode")
public class VerificationCodeRemoteServiceImpl implements VerificationCodeRemoteService {

    @Autowired
    IVerificationCodeService verificationCodeService;

    @RequestMapping("sendCode")
    @Override
    public ResponseDTO<?> sendCode(String telephone) {
        ResponseDTO<?> responseDTO;
        try {
            verificationCodeService.save(telephone);
            responseDTO = ResponseDTO.success("发送成功");
        } catch (Exception e) {
            responseDTO = ResponseDTO.ofError(e.getMessage());
        }
        return responseDTO;
    }

    /**
     * 验证码不正确,设置状态码为203
     * 验证码过期,设置状态码为204
     *
     * @param telephone 手机号
     * @param code      验证码
     *
     * @return 结果
     */
    @RequestMapping("checkCode")
    @Override
    public ResponseDTO<?> checkCode(String telephone, String code) {
        ResponseDTO<?> responseDTO;
        try {
            verificationCodeService.checkCode(telephone, code);
            responseDTO = ResponseDTO.success("验证成功");
        } catch (IncorrectCodeRuntimteException e) {
            responseDTO = ResponseDTO.response(203, e.getMessage(), null);
        } catch (ExpireCodeRuntimeException e) {
            responseDTO = ResponseDTO.response(204, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = ResponseDTO.ofError(e.getMessage());
        }
        return responseDTO;

    }
}
