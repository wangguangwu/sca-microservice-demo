package com.wangguangwu.feign;

import com.wangguangwu.common.dto.PaymentInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.feign.fallback.PaymentServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Payment-Service 服务的 Feign 客户端
 *
 * @author wangguangwu
 */
@FeignClient(name = "payment-service", fallback = PaymentServiceClientFallback.class)
public interface PaymentServiceClient {

    /**
     * 获取 Payment-Service 的信息
     *
     * @return Payment-Service 的信息
     */
    @GetMapping("/getServiceProperties")
    Result<PaymentInfoDTO> getServiceProperties();
}
