package com.wangguangwu.controller;

import com.wangguangwu.common.dto.PaymentInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付服务控制器
 * 提供支付相关信息的接口，供其他服务调用
 *
 * @author wangguangwu
 */
@RestController
@RequiredArgsConstructor
public class PaymentController {

    /**
     * 支付服务配置属性
     * 通过构造器注入，由Spring自动装配
     */
    private final PaymentProperties paymentProperties;

    /**
     * 获取用户服务属性
     * 将用户配置属性转换为UserConfigInfo对象返回
     *
     * @return 用户服务配置信息
     */
    @GetMapping("/getServiceProperties")
    public Result<PaymentInfoDTO> getServiceProperties() {
        return Result.success(
                new PaymentInfoDTO(
                        paymentProperties.getCurrency(),
                        paymentProperties.getRetryMax(),
                        paymentProperties.getTimeoutMs()
                )
        );
    }
}
