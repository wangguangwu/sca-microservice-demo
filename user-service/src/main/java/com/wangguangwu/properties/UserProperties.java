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
@ConfigurationProperties(prefix = "user")
@RefreshScope
public class UserProperties {

    private String name;
    private String level;
    private Boolean featureEnabled;

}
