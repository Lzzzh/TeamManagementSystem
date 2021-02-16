package com.team.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName(value = "selection")
@ApiModel(value = "项目选择情况表")
public class Selection {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "学号")
    private String studentId;

    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "项目标识")
    private String projectId;

    @ApiModelProperty(value = "项目名")
    private String projectName;
}
