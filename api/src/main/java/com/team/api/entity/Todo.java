package com.team.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@ApiModel("待办清单")
@TableName("todolist")
public class Todo {

    @ApiModelProperty("主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("唯一标识")
    private String todoId;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private Boolean status;

}
