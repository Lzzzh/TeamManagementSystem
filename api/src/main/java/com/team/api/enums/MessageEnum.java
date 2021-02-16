package com.team.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnum {

    SUCCESS("成功！"),
    FAIL("失败！");
    private String message;
}
