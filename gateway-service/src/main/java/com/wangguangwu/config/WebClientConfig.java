package com.wangguangwu.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClient 配置类
 *
 * @author wangguangwu
 */
@Configuration
public class WebClientConfig {

    /**
     * 配置支持负载均衡的 WebClient.Builder
     *
     * @return WebClient.Builder 实例
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }
}
