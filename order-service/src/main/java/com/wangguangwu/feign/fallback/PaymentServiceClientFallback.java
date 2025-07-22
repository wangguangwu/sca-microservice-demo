package com.wangguangwu.feign.fallback;

import com.wangguangwu.common.dto.PaymentInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.feign.PaymentServiceClient;
import org.springframework.stereotype.Component;

/**
 * Payment-Service 服务的 Feign 客户端降级实现
 *
 * @author wangguangwu
 */
@Component
public class PaymentServiceClientFallback implements PaymentServiceClient {

    @Override
    public Result<PaymentInfoDTO> getServiceProperties() {
        return Result.fail("Payment-Service 暂时不可用");
    }
}
