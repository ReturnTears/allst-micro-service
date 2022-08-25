package com.allst.micro.remote;

import com.allst.micro.dto.PromotionSpaceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/space/getAllSpaces")
    List<PromotionSpaceDto> getAllSpaces();
}
