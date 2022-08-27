package com.allst.micro.remote;

import com.allst.micro.dto.PromotionAdDto;
import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.response.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 使用OpenFeign远程调用广告位Space对应Service
 *  name属性需和allst-micro-ad-impl模块的实例应用名称保持一直
 *  path属性需和allst-micro-ad-impl模块的广告位顶层接口路径一直
 *
 * @author Hutu
 * @since 2022-08-25 下午 03:47
 */
@FeignClient(name = "allst-micro-ad", path = "/ad")
public interface AdSpaceRemoteService {

    /**
     * 获取所有的广告位
     */
    @GetMapping("/space/getAllSpaces")
    List<PromotionSpaceDto> getAllSpaces();

    /**
     * 通过广告位获取所有的广告
     * @param spaceKey 广告位对应Key
     * @return 结果
     */
    @GetMapping("/getAdBySpaceKey")
    List<PromotionSpaceDto> getAdBySpaceKey(@RequestParam("spaceKey") String[] spaceKey);

    /**
     * 新增或修改广告位
     *
     * @param spaceDto 参数
     * @return 结果
     */
    @PostMapping("/space/saveOrUpdateSpace")
    ResponseDTO<?> saveOrUpdateSpace(PromotionSpaceDto spaceDto);

    /**
     * 通过id获取广告位
     *
     * @param id 广告位id
     * @return 结果
     */
    @GetMapping("/space/getSpaceById")
    PromotionSpaceDto getSpaceById(@RequestParam("id") Integer id);

    /**
     * 获取所有的广告
     *
     * @return 结果
     */
    @GetMapping("/getAllAds")
    List<PromotionAdDto> getAllAds();
}
