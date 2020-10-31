package com.eureka.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eureka.springcloud.entities.Payment;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author: swd
 * @since 2020-09-11
 */
public interface IPaymentService extends IService<Payment> {

    void create(Payment payment);

    Payment getPaymentById(Long id);
}
