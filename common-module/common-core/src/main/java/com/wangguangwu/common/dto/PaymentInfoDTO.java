package com.wangguangwu.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangguangwu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfoDTO {

    private String currency;
    private Integer retryMax;
    private Integer timeoutMs;

}
