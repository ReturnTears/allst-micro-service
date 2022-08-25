package com.allst.micro.ad.controller;


import com.allst.micro.ad.entity.PromotionSpace;
import com.allst.micro.ad.service.IPromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Hutu
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/ad/space")
public class PromotionSpaceController {

    @Autowired
    IPromotionSpaceService spaceService;

    @RequestMapping("/getAllSpaces")
    public List<PromotionSpace> getAllSpaces() {
        return spaceService.list();
    }
}
