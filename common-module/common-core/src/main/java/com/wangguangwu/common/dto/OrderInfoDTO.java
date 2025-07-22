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
public class OrderInfoDTO {

    private Integer maxPerUser;
    private Integer autoCancelMinutes;
    private Boolean featureFlashSale;

}
