package com.temp.base.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 描述
 * </p>
 *
 * @author tx
 * @since 2019/5/26 23:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonUser {
    private String name;
    private Integer age;
    private String mobile;
}
