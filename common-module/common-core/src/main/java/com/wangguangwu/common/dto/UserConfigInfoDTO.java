package com.wangguangwu.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户配置信息类
 * 用于封装用户配置的核心信息
 *
 * @author wangguangwu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigInfoDTO {

    private boolean featureEnabled;
    private String level;
    private String name;

}
