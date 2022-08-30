package com.allst.micro.service.impl;

import com.allst.micro.date.DateUtil;
import com.allst.micro.entity.VerificationCode;
import com.allst.micro.exception.ExpireCodeRuntimeException;
import com.allst.micro.exception.IncorrectCodeRuntimteException;
import com.allst.micro.mapper.VerificationCodeMapper;
import com.allst.micro.random.RandomUtil;
import com.allst.micro.service.IVerificationCodeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Slf4j
@Service
public class VerificationCodeServiceImpl extends ServiceImpl<VerificationCodeMapper, VerificationCode> implements IVerificationCodeService {

    @Override
    public boolean save(String telephone) {
        // 获取动态验证码
        String randomNumber = RandomUtil.getRandomNumber(6);
        // 发送验证码
        log.info("发送验证码");

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setPhone(telephone);   //设置电话号码
        verificationCode.setVerificationCode(randomNumber);
        verificationCode.setCreateTime(new Date());
        verificationCode.setIsCheck(false);
        verificationCode.setCheckTimes(0);

        // 保存验证码
        return this.save(verificationCode);
    }

    @Override
    public boolean checkCode(String telephone, String code) {
        //判断验证码是否存在
        QueryWrapper<VerificationCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", telephone);
        queryWrapper.eq("verification_code", code);
        VerificationCode verificationCode = this.getBaseMapper().selectOne(queryWrapper);

        if (verificationCode == null) {
            log.error("验证码错误");
            throw new IncorrectCodeRuntimteException("验证码错误");
        }
        //判断验证码是否过期
        Date now = new Date();
        Date expireDate = DateUtil.rollByMinutes(verificationCode.getCreateTime(), 5);
        if (now.getTime() > expireDate.getTime()) {
            log.error("验证码失效,重新发送");
            throw new ExpireCodeRuntimeException("验证码失效,重新发送");
        }

        verificationCode.setIsCheck(true);
        int checkTimes = verificationCode.getCheckTimes() + 1;
        verificationCode.setCheckTimes(checkTimes);

        return this.updateById(verificationCode);
    }
}
