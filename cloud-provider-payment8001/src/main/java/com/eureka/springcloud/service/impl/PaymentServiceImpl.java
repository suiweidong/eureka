package com.eureka.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eureka.springcloud.entities.Payment;
import com.eureka.springcloud.mapper.PaymentMapper;
import com.eureka.springcloud.service.IPaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author: swd
 * @since 2020-09-11
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

    @Resource
    private PaymentMapper paymentMapper;

    @Override
    public void create(Payment payment) {
        int result = this.paymentMapper.insert(payment);
        if (result <= 0){
            throw new RuntimeException("插入失败");
        }
    }

    @Override
    public Payment getPaymentById(Long id) {
        return this.paymentMapper.selectById(id);
    }
}
