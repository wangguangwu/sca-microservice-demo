package com.wangguangwu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author wangguangwu
 */
@Data
@Component
@ConfigurationProperties(prefix = "payment")
@RefreshScope
public class PaymentProperties {

    private String currency;
    private Integer retryMax;
    private Integer timeoutMs;

}
