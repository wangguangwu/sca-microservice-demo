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
@ConfigurationProperties(prefix = "order")
@RefreshScope
public class OrderProperties {

    private Integer maxPerUser;
    private Integer autoCancelMinutes;
    private Boolean featureFlashSale;

}
