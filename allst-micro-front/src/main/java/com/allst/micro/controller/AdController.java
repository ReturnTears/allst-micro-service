package com.allst.micro.controller;

import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import com.allst.micro.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Hutu
 * @since 2022-08-26 下午 10:48
 */
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdSpaceRemoteService remoteService;

    /**
     * 通过广告位Key获取广告位信息及其广告数据
     *
     * @param spaceKey 广告位Key
     *
     * @return 结果
     */
    @GetMapping("/getAdBySpaceKey")
    public ResponseDTO<?> getAdBySpaceKey(@RequestParam("spaceKey") String[] spaceKey) {
        List<PromotionSpaceDto> dtoList = remoteService.getAdBySpaceKey(spaceKey);

        return ResponseDTO.success(dtoList);
    }

}
