package com.allst.micro.ad.remote;

import com.allst.micro.Util.ConverUtil;
import com.allst.micro.ad.entity.PromotionAd;
import com.allst.micro.ad.entity.PromotionSpace;
import com.allst.micro.ad.service.IPromotionAdService;
import com.allst.micro.ad.service.IPromotionSpaceService;
import com.allst.micro.dto.PromotionAdDto;
import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 远程调用Ad Space广告位接口的实现类
 *  编写该实现类后，当前模块中就不再需要PromotionSpaceController前端控制器了，直接使用远程调用即可访问当前服务对应的实现内容
 *
 * @author Hutu
 * @since 2022-08-25 下午 04:03
 */
@RestController
@RequestMapping("/ad")
public class AdSpaceRemoteServiceImpl implements AdSpaceRemoteService {

    @Autowired
    IPromotionSpaceService spaceService;

    @Autowired
    IPromotionAdService adService;

    @GetMapping("/space/getAllSpaces")
    @Override
    public List<PromotionSpaceDto> getAllSpaces() {
        List<PromotionSpace> list = spaceService.list();
        return ConverUtil.convertList(list, PromotionSpaceDto.class);
    }

    @GetMapping("/getAdBySpaceKey")
    @Override
    public List<PromotionSpaceDto> getAdBySpaceKey(String[] spaceKey) {
        List<PromotionSpaceDto> spaceDtoList = Lists.newArrayList();
        if (spaceKey.length == 0) {
            return spaceDtoList;
        }
        final Date now = new Date();
        for (String key : spaceKey) {
            QueryWrapper<PromotionSpace> spaceWrapper = new QueryWrapper<>();
            // 查询指定广告位
            spaceWrapper.eq("spaceKey", key);
            PromotionSpace promotionSpace = spaceService.getOne(spaceWrapper);

            // 查询广告位对应广告，广告需有效且在正常时效内
            QueryWrapper<PromotionAd> adWrapper = new QueryWrapper<>();
            adWrapper.eq("spaceId", promotionSpace.getId());
            adWrapper.eq("status", 1);
            adWrapper.lt("startTime", now);
            adWrapper.gt("endTime", now);
            List<PromotionAd> promotionAdList = adService.list(adWrapper);

            // 需转换实体对象
            PromotionSpaceDto spaceDto = ConverUtil.convert(promotionSpace, PromotionSpaceDto.class);

            List<PromotionAdDto> adDto = ConverUtil.convertList(promotionAdList, PromotionAdDto.class);
            assert spaceDto != null;
            spaceDto.setAdDtoList(adDto);

            spaceDtoList.add(spaceDto);
        }

        return spaceDtoList;
    }
}
