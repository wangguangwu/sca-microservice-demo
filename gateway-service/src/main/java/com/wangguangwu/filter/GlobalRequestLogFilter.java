package com.wangguangwu.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局请求日志过滤器
 * 记录所有经过网关的请求信息
 *
 * @author wangguangwu
 */
@Slf4j
@Component
public class GlobalRequestLogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        String method = request.getMethod().name();
        
        log.info("收到请求 - 路径: {}, 方法: {}, 来源IP: {}", 
                path, 
                method, 
                request.getRemoteAddress());
        
        // 记录请求开始时间
        long startTime = System.currentTimeMillis();
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 记录请求处理时间
            long duration = System.currentTimeMillis() - startTime;
            log.info("请求处理完成 - 路径: {}, 耗时: {}ms", path, duration);
        }));
    }

    @Override
    public int getOrder() {
        // 设置为最高优先级，确保这个过滤器最先执行
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
