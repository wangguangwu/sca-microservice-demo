package com.wangguangwu.feign.fallback;

import com.wangguangwu.common.dto.UserConfigInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.feign.UserServiceClient;
import org.springframework.stereotype.Component;

/**
 * User-Service 服务的 Feign 客户端降级实现
 *
 * @author wangguangwu
 */
@Component
public class UserServiceClientFallback implements UserServiceClient {

    @Override
    public Result<UserConfigInfoDTO> getServiceProperties() {
        return Result.fail("User-Service 服务暂时不可用");
    }
}
