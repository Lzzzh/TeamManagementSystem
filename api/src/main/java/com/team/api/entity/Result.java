package com.team.api.entity;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@ApiModel(value = "统一返回数据格式")
public class Result<T> {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("Json数据")
    private T data;

    public static<T> Result<T> success(String message, T data) {
        return new Result<>(HttpStatus.HTTP_OK, message, data);
    }

    public static<T> Result<T> fail(String message, T data) {
        return new Result<>(HttpStatus.HTTP_INTERNAL_ERROR, message, data);
    }
}
