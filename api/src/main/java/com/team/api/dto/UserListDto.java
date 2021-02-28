package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/** 用于构造联级选择器传值
 * @author liuzhaohao
 * @date 2021/2/25 10:19 下午
 * @param
 * @return
 */
@Data
@AllArgsConstructor
public class UserListDto {

    private String label;

    private String value;

    private List<?> children;
}
