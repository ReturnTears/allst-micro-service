package com.allst.micro.service.impl;

import com.allst.micro.entity.po.Media;
import com.allst.micro.mapper.MediaMapper;
import com.allst.micro.service.IMediaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Hutu
 * @since 2022-09-22 下午 09:56
 */
@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media> implements IMediaService {

}
