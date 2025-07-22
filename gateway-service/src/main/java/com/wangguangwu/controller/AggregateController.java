package com.wangguangwu.controller;

import com.wangguangwu.common.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 聚合服务控制器
 * <p>
 * 提供汇聚调用 user/order/payment 服务的接口
 *
 * @author wangguangwu
 */
@RestController
@RequestMapping("/aggregate")
@RequiredArgsConstructor
public class AggregateController {

    private final WebClient.Builder webClientBuilder;

    /**
     * 聚合接口 - 获取所有服务信息
     * <p>
     * 汇聚调用 user/order/payment 服务，返回综合信息
     *
     * @return 聚合的服务信息
     */
    @GetMapping("/test/all")
    public Mono<Map<String, Object>> getAllServicesInfo() {
        return aggregateServiceInfo();
    }

    /**
     * 聚合调用各个微服务的信息
     *
     * @return 聚合后的服务信息
     */
    private Mono<Map<String, Object>> aggregateServiceInfo() {
        Mono<Map<String, Object>> userInfo = webClientBuilder.build()
                .get()
                .uri("http://user-service/getServiceProperties")
                .retrieve()
                .bodyToMono(Result.class)
                .map(result -> Map.of("success", true, "data", result))
                .onErrorResume(e -> Mono.just(Map.of("success", false, "error", "用户服务不可用: " + e.getMessage())));

        Mono<Map<String, Object>> orderInfo = webClientBuilder.build()
                .get()
                .uri("http://order-service/getServiceProperties/feign")
                .retrieve()
                .bodyToMono(Result.class)
                .map(result -> Map.of("success", true, "data", result))
                .onErrorResume(e -> Mono.just(Map.of("success", false, "error", "订单服务不可用: " + e.getMessage())));

        Mono<Map<String, Object>> paymentInfo = webClientBuilder.build()
                .get()
                .uri("http://payment-service/getServiceProperties")
                .retrieve()
                .bodyToMono(Result.class)
                .map(result -> Map.of("success", true, "data", result))
                .onErrorResume(e -> Mono.just(Map.of("success", false, "error", "支付服务不可用: " + e.getMessage())));

        return Mono.zip(userInfo, orderInfo, paymentInfo)
                .map(tuple -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("timestamp", System.currentTimeMillis());
                    result.put("gateway", "Gateway Aggregation Service");
                    result.put("userService", tuple.getT1());
                    result.put("orderService", tuple.getT2());
                    result.put("paymentService", tuple.getT3());
                    return result;
                });
    }
}
