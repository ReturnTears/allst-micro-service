package com.allst.micro.ad.remote;

import com.allst.micro.Util.ConverUtil;
import com.allst.micro.ad.entity.PromotionAd;
import com.allst.micro.ad.entity.PromotionSpace;
import com.allst.micro.ad.service.IPromotionAdService;
import com.allst.micro.ad.service.IPromotionSpaceService;
import com.allst.micro.dto.PromotionAdDto;
import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import com.allst.micro.response.ResponseDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 远程调用Ad Space广告位接口的实现类
 * 编写该实现类后，当前模块中就不再需要PromotionSpaceController前端控制器了，直接使用远程调用即可访问当前服务对应的实现内容
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
        if (list == null || list.size() == 0) {
            return Lists.newArrayList();
        }
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

    @PostMapping("/space/saveOrUpdateSpace")
    @Override
    public ResponseDTO<?> saveOrUpdateSpace(PromotionSpaceDto spaceDto) {
        PromotionSpace space = ConverUtil.convert(spaceDto, PromotionSpace.class);

        assert space != null;
        final Date now = new Date();
        if (space.getId() != null) {
            space.setCreateTime(now);
            space.setUpdateTime(now);
            space.setIsDel(0); // 0 不删除 1删除
        } else {
            space.setUpdateTime(now);
        }

        ResponseDTO<?> responseDTO = null;
        try {
            spaceService.saveOrUpdate(space);
            responseDTO = ResponseDTO.success();
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = ResponseDTO.ofError(e.getMessage());
        }

        return responseDTO;
    }

    @GetMapping("/space/getSpaceById")
    @Override
    public PromotionSpaceDto getSpaceById(Integer id) {
        PromotionSpace space = spaceService.getById(id);
        if (space == null) {
            return new PromotionSpaceDto();
        }

        return ConverUtil.convert(space, PromotionSpaceDto.class);
    }

    @GetMapping("/getAllAds")
    @Override
    public List<PromotionAdDto> getAllAds() {
        List<PromotionAd> list = adService.list();
        if (list == null || list.size() == 0) {
            return Lists.newArrayList();
        }

        return ConverUtil.convertList(list, PromotionAdDto.class);
    }

    @PostMapping("/saveOrUpdateAd")
    @Override
    public ResponseDTO<?> saveOrUpdateAd(PromotionAdDto adDto) {
        PromotionAd ad = ConverUtil.convert(adDto, PromotionAd.class);
        assert ad != null;
        final Date now = new Date();
        if (ad.getId() == null) {
            ad.setCreateTime(now);
            ad.setUpdateTime(now);
            ad.setStatus(1); // 0无效 1有效
        } else {
            ad.setUpdateTime(now);
        }
        ResponseDTO<?> responseDTO = null;
        try {
            adService.saveOrUpdate(ad);
            responseDTO = ResponseDTO.success();
        } catch (Exception e) {
            responseDTO = ResponseDTO.ofError(e.getMessage());
        }
        return responseDTO;
    }

    @GetMapping("/getAdById")
    @Override
    public PromotionAdDto getAdById(Integer id) {
        PromotionAd ad = adService.getById(id);
        if (ad == null) {
            return new PromotionAdDto();
        }
        return ConverUtil.convert(ad, PromotionAdDto.class);
    }
}
