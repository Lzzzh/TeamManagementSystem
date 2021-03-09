package com.team.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/** 用于构造联级选择器传值
 * @author liuzhaohao
 * @date 2021/2/25 3:22 下午
 * @param
 * @return
 */
@Data
@AllArgsConstructor
public class StudentListDto {

    private String label;

    private String value;

    private String className;
}
