package com.allst.micro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.allst.micro.entity.VerificationCode;

/**
 * <p>
 *  验证码服务类
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
public interface IVerificationCodeService extends IService<VerificationCode> {

    void save(String telephone);

    void checkCode(String telephone, String code);
}
