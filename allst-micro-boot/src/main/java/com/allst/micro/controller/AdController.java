package com.allst.micro.controller;

import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import com.allst.micro.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Ad广告前端控制器
 *
 * @author Hutu
 * @since 2022-08-25 下午 04:17
 */
@RestController
@RequestMapping("/ad")
public class AdController {

    @Autowired
    AdSpaceRemoteService remoteService;

    /**
     * 获取广告位列表
     *
     * @return 结果
     */
    @GetMapping("/space/getAllSpaces")
    public ResponseDTO<?> getAllSpaces() {
        List<PromotionSpaceDto> dtoList = remoteService.getAllSpaces();

        return ResponseDTO.success(dtoList);
    }

    /**
     * 新增或修改广告位
     *
     * @return 结果
     */
    @PostMapping("/space/saveOrUpdateSpace")
    public ResponseDTO<?> saveOrUpdateSpace(@RequestBody PromotionSpaceDto spaceDto) {
        return remoteService.saveOrUpdateSpace(spaceDto);
    }
}
