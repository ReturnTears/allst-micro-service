package com.allst.micro.service.impl;

import com.allst.micro.entity.PayOrderRecord;
import com.allst.micro.mapper.PayOrderRecordMapper;
import com.allst.micro.service.IPayOrderRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Hutu
 * @since 2022-10-16 下午 04:14
 */
@Slf4j
@Service
public class PayOrderRecordServiceImpl extends ServiceImpl<PayOrderRecordMapper, PayOrderRecord> implements IPayOrderRecordService {

}
