package com.wangguangwu.controller;

import com.alibaba.fastjson2.JSONObject;
import com.wangguangwu.common.dto.OrderInfoDTO;
import com.wangguangwu.common.dto.PaymentInfoDTO;
import com.wangguangwu.common.dto.UserConfigInfoDTO;
import com.wangguangwu.common.entity.Result;
import com.wangguangwu.feign.PaymentServiceClient;
import com.wangguangwu.feign.UserServiceClient;
import com.wangguangwu.properties.OrderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * 订单服务控制器
 * 提供两种微服务调用方式：
 * 1. 使用OpenFeign进行声明式服务调用
 * 2. 使用RestTemplate进行服务调用
 *
 * @author wangguangwu
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    /**
     * 订单服务配置属性
     */
    private final OrderProperties orderProperties;

    /**
     * 支付服务Feign客户端
     */
    private final PaymentServiceClient paymentServiceClient;

    /**
     * 用户服务Feign客户端
     */
    private final UserServiceClient userServiceClient;

    /**
     * RestTemplate客户端，已通过@LoadBalanced注解实现负载均衡
     */
    private final RestTemplate restTemplate;

    /**
     * 使用OpenFeign方式获取订单完整信息
     * 通过Feign客户端调用支付服务和用户服务
     *
     * @return 包含订单、支付和用户信息的JSON字符串
     */
    @GetMapping("/getServiceProperties/feign")
    public JSONObject getServicePropertiesWithFeign() {
        // 调用支付服务获取支付信息
        Result<PaymentInfoDTO> paymentResult = paymentServiceClient.getServiceProperties();
        PaymentInfoDTO paymentInfo = Objects.requireNonNull(paymentResult).getData();

        // 调用用户服务获取用户信息
        Result<UserConfigInfoDTO> userResult = userServiceClient.getServiceProperties();
        UserConfigInfoDTO userInfo = Objects.requireNonNull(userResult).getData();

        // 组装返回结果
        return buildResponseJson("Feign", paymentInfo, userInfo);
    }

    /**
     * 使用RestTemplate方式获取订单完整信息
     * 通过RestTemplate调用支付服务和用户服务
     *
     * @return 包含订单、支付和用户信息的JSON字符串
     */
    @GetMapping("/getServiceProperties/rest")
    public String getServicePropertiesWithRestTemplate() {
        // 调用支付服务获取支付信息，使用泛型获取Result<PaymentInfoDTO>
        ResponseEntity<Result<PaymentInfoDTO>> paymentResponse = restTemplate.exchange(
                "http://payment-service/getServiceProperties",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        PaymentInfoDTO paymentInfo = Objects.requireNonNull(paymentResponse.getBody()).getData();

        // 调用用户服务获取用户信息，使用泛型获取Result<UserConfigInfoDTO>
        ResponseEntity<Result<UserConfigInfoDTO>> userResponse = restTemplate.exchange(
                "http://user-service/getServiceProperties",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        UserConfigInfoDTO userInfo = Objects.requireNonNull(userResponse.getBody()).getData();

        // 组装返回结果
        JSONObject result = buildResponseJson("RestTemplate", paymentInfo, userInfo);
        return result.toJSONString();
    }

    /**
     * 构建统一的响应JSON对象
     *
     * @param type        调用类型（Feign或RestTemplate）
     * @param paymentInfo 支付服务返回的信息
     * @param userInfo    用户服务返回的信息
     * @return 组装后的JSON对象
     */
    private JSONObject buildResponseJson(String type, Object paymentInfo, Object userInfo) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO(orderProperties.getMaxPerUser(), orderProperties.getAutoCancelMinutes(), orderProperties.getFeatureFlashSale());

        JSONObject result = new JSONObject();
        result.put("type", type);
        result.put("orderInfo", orderInfoDTO);
        result.put("paymentInfo", paymentInfo);
        result.put("userInfo", userInfo);
        return result;
    }
}
