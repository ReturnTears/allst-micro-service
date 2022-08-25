package com.allst.micro.ad.remote;

import com.allst.micro.Util.ConverUtil;
import com.allst.micro.ad.entity.PromotionSpace;
import com.allst.micro.ad.service.IPromotionSpaceService;
import com.allst.micro.dto.PromotionSpaceDto;
import com.allst.micro.remote.AdSpaceRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/space/getAllSpaces")
    @Override
    public List<PromotionSpaceDto> getAllSpaces() {
        List<PromotionSpace> list = spaceService.list();
        return ConverUtil.convertList(list, PromotionSpaceDto.class);
    }
}
