package com.wangguangwu.feign;

import com.wangguangwu.common.dto.UserConfigInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.feign.fallback.UserServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * User-Service 服务的 Feign 客户端
 *
 * @author wangguangwu
 */
@FeignClient(name = "user-service", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    /**
     * 获取 User-Service 的信息
     *
     * @return User-Service 的信息
     */
    @GetMapping("/getServiceProperties")
    Result<UserConfigInfoDTO> getServiceProperties();
}
