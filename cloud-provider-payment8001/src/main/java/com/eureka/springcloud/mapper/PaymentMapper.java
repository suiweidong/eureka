package com.eureka.springcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eureka.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author: swd
 * @since 2020-09-11
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
