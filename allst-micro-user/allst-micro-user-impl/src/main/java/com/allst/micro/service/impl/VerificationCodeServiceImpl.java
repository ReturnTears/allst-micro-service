package com.allst.micro.service.impl;

import com.allst.micro.entity.VerificationCode;
import com.allst.micro.mapper.VerificationCodeMapper;
import com.allst.micro.service.IVerificationCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements IVerificationCodeService {

    @Override
    public boolean save(String telephone) {
        return false;
    }

    @Override
    public boolean checkCode(String telephone, String code) {
        return false;
    }
}
