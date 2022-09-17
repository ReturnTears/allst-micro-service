package com.allst.micro.remote;

import cn.hutool.core.bean.BeanUtil;
import com.allst.micro.date.DateUtil;
import com.allst.micro.dto.WeixinDTO;
import com.allst.micro.entity.Weixin;
import com.allst.micro.response.ResponseDTO;
import com.allst.micro.service.IWeixinService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 用户微信远程调用服务实现类
 *
 * @author Hutu
 * @since 2022-08-27 下午 06:26
 */
@Slf4j
@Service
//@RestController
//@RequestMapping("/user/weixin")
public class WeixinRemoteServiceImpl implements WeixinRemoteService {

    @Autowired
    IWeixinService weixinService;

    //@GetMapping("/getUserWeixinByUserId")
    @Override
    public WeixinDTO getUserWeixinByUserId(Integer userId) {
        LambdaQueryWrapper<Weixin> wrapper = new QueryWrapper<Weixin>().lambda()
                .eq(Weixin::getUserId, userId)
                .eq(Weixin::getIsDel, false);
        List<Weixin> weixins = this.weixinService.getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isEmpty(weixins)) {
            return null;
        }
        WeixinDTO dto = new WeixinDTO();
        BeanUtil.copyProperties(weixins.get(0), dto);
        return dto;
    }

    //@GetMapping("/getUserWeixinByOpenId")
    @Override
    public WeixinDTO getUserWeixinByOpenId(String openId) {
        LambdaQueryWrapper<Weixin> wrapper = new QueryWrapper<Weixin>().lambda()
                .eq(Weixin::getOpenId, openId)
                .eq(Weixin::getIsDel, false);
        List<Weixin> weixins = this.weixinService.getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isEmpty(weixins)) {
            return null;
        }
        WeixinDTO dto = new WeixinDTO();
        BeanUtil.copyProperties(weixins.get(0), dto);
        return dto;
    }

    //@GetMapping("/getUserWeixinByUnionId")
    @Override
    public WeixinDTO getUserWeixinByUnionId(String unionId) {
        LambdaQueryWrapper<Weixin> wrapper = new QueryWrapper<Weixin>().lambda()
                .eq(Weixin::getUnionId, unionId)
                .eq(Weixin::getIsDel, false);
        List<Weixin> weixins = this.weixinService.getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isEmpty(weixins)) {
            return null;
        }
        WeixinDTO dto = new WeixinDTO();
        BeanUtil.copyProperties(weixins.get(0), dto);
        return dto;
    }

    //@PostMapping("/saveUserWeixin")
    @Override
    public WeixinDTO saveUserWeixin(WeixinDTO weixinDTO) {
        Weixin weixin = new Weixin();
        final Date now = new Date();
        BeanUtil.copyProperties(weixinDTO, weixin);
        weixin.setCreateTime(now);
        weixin.setUpdateTime(now);
        weixin.setIsDel(false);
        weixin.setId(null);
        boolean result = this.weixinService.save(weixin);
        log.info("微信绑定成功,微信:{}, 结果:{}", weixin, result);
        WeixinDTO dto = new WeixinDTO();
        BeanUtil.copyProperties(weixin, dto);
        return dto;
    }

    //@PostMapping("/updateUserWeixin")
    @Override
    public boolean updateUserWeixin(WeixinDTO weixinDTO) {
        Weixin weixin = new Weixin();
        BeanUtil.copyProperties(weixinDTO, weixin, "id", "create_time");
        weixin.setUpdateTime(new Date());
        weixin.setIsDel(false);
        boolean result = this.weixinService.updateById(weixin);
        log.info("微信绑定成功,微信:{}, 结果:{}", weixin, result);
        return true;
    }

    //@PostMapping("/bindUserWeixin")
    @Override
    public ResponseDTO<WeixinDTO> bindUserWeixin(WeixinDTO weixinDTO) {
        LambdaQueryWrapper<Weixin> wrapper = new QueryWrapper<Weixin>().lambda().eq(Weixin::getUserId, weixinDTO.getUserId())
                .eq(Weixin::getUnionId, weixinDTO.getUnionId()).eq(Weixin::getIsDel, false);
        List<Weixin> weixins = this.weixinService.getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isNotEmpty(weixins)) {
            log.info("userId:{}, unionId:{}, openId:{} 已绑定，不用处理 ", weixinDTO.getUserId(), weixinDTO.getUnionId(), weixinDTO.getOpenId());
            return ResponseDTO.response(200, "已绑定，无需处理");
        }
        // 该用户已绑定其他unionId
        Weixin userIdWeixin = this.weixinService.getBaseMapper().selectOne(new QueryWrapper<Weixin>().lambda()
                .eq(Weixin::getUserId, weixinDTO.getUserId()).eq(Weixin::getIsDel, false).orderByDesc(Weixin::getId));
        if (null != userIdWeixin && !StringUtils.equals(weixinDTO.getUnionId(), userIdWeixin.getUnionId())) {
            log.info("userId:{}, unionId:{}, openId:{} 该用户已绑定其他unionId ", weixinDTO.getUserId(), weixinDTO.getUnionId(), weixinDTO.getOpenId());
            return ResponseDTO.response(201, "该用户已绑定其他unionId");
        }
        // 该unionId已绑定其他userId
        Weixin unionIdWeixin = this.weixinService.getBaseMapper().selectOne(new QueryWrapper<Weixin>().lambda()
                .eq(Weixin::getUnionId, weixinDTO.getUnionId()).eq(Weixin::getIsDel, false).orderByDesc(Weixin::getId));
        if (null != unionIdWeixin && null != weixinDTO.getUnionId() && !weixinDTO.getUserId().equals(unionIdWeixin.getUserId())) {
            log.info("userId:{}, unionId:{}, openId:{} 该unionId已绑定其他用户 ", weixinDTO.getUserId(), weixinDTO.getUnionId(), weixinDTO.getOpenId());
            return ResponseDTO.response(202, "该unionId已绑定其他用户");
        }
        // 开始真正绑定
        Weixin weixin = new Weixin();
        BeanUtil.copyProperties(weixinDTO, weixin);
        weixin.setCreateTime(new Date());
        weixin.setUpdateTime(new Date());
        weixin.setIsDel(false);
        weixin.setId(null);
        boolean result = this.weixinService.save(weixin);
        log.info("微信绑定成功,微信:{}, 结果:{}", weixins, result);
        WeixinDTO dto = new WeixinDTO();
        BeanUtil.copyProperties(weixin, dto);
        return ResponseDTO.success(dto);
    }

    //@PostMapping("/unBindUserWeixin")
    @Override
    public boolean unBindUserWeixin(Integer userId) {
        LambdaQueryWrapper<Weixin> wrapper = new QueryWrapper<Weixin>().lambda().eq(Weixin::getUserId, userId).eq(Weixin::getIsDel, false);
        List<Weixin> weixins = this.weixinService.getBaseMapper().selectList(wrapper);
        if (CollectionUtils.isEmpty(weixins)) {
            return true;
        }
        weixins.forEach(weixin -> {
            weixin.setIsDel(true);
            weixin.setUpdateTime(DateUtil.getNowDate());
            weixinService.updateById(weixin);
            log.info("用户[{}]已解绑微信[{}]", userId, weixin.getOpenId());
        });
        return true;
    }
}
