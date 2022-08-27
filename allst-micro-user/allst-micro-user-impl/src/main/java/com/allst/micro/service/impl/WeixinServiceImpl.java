package com.allst.micro.service.impl;

import com.allst.micro.entity.Weixin;
import com.allst.micro.mapper.WeixinMapper;
import com.allst.micro.service.IWeixinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户绑定微信服务实现类
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@Service
public class WeixinServiceImpl extends ServiceImpl<WeixinMapper, Weixin> implements IWeixinService {

}
