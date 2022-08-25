package com.allst.micro.controller;

import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import com.allst.micro.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/space/getAllSpaces")
    public ResponseDTO<?> getAllSpaces() {
        List<PromotionSpaceDto> dtoList = remoteService.getAllSpaces();

        return ResponseDTO.success(dtoList);
    }

}
