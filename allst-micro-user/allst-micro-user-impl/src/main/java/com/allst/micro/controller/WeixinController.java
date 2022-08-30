package com.allst.micro.controller;

import com.allst.micro.dto.WeixinDTO;
import com.allst.micro.remote.WeixinRemoteService;
import com.allst.micro.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Hutu
 * @since 2022-08-27 下午 04:54
 */
@RestController
@RequestMapping("/user/weixin")
public class WeixinController {

    @Autowired
    WeixinRemoteService weixinRemoteService;

    @GetMapping("/getUserWeixinByUserId")
    public WeixinDTO getUserWeixinByUserId(@RequestParam("userId") Integer userId) {
        return this.weixinRemoteService.getUserWeixinByUserId(userId);
    }

    @GetMapping("/getUserWeixinByOpenId")
    public WeixinDTO getUserWeixinByOpenId(@RequestParam("openId") String openId) {
        return this.weixinRemoteService.getUserWeixinByOpenId(openId);
    }

    @GetMapping("/getUserWeixinByUnionId")
    public WeixinDTO getUserWeixinByUnionId(@RequestParam("unionId") String unionId) {
        return this.weixinRemoteService.getUserWeixinByUnionId(unionId);
    }

    @PostMapping("/saveUserWeixin")
    public WeixinDTO saveUserWeixin(@RequestBody WeixinDTO weixinDTO) {
        return this.weixinRemoteService.saveUserWeixin(weixinDTO);
    }

    @PostMapping("/updateUserWeixin")
    public boolean updateUserWeixin(@RequestBody WeixinDTO weixinDTO) {
        return this.weixinRemoteService.updateUserWeixin(weixinDTO);
    }

    @PostMapping(value = "/bindUserWeixin")
    public ResponseDTO<WeixinDTO> bindUserWeixin(@RequestBody WeixinDTO weixinDTO) {
        return this.weixinRemoteService.bindUserWeixin(weixinDTO);
    }

    @PostMapping("/unBindUserWeixin")
    public boolean unBindUserWeixin(@RequestParam("userId") Integer userId) {
        return this.weixinRemoteService.unBindUserWeixin(userId);
    }
}