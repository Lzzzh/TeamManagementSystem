package com.team.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;

@Data
@ApiModel("项目")
@TableName(value = "project")
public class Project {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "项目唯一标识")
    private String projectId;

    @ApiModelProperty(value = "项目名")
    private String projectName;

    @ApiModelProperty(value = "项目进度")
    private Integer progress;

    @ApiModelProperty(value = "指导老师ID")
    private String teacherId;

    @ApiModelProperty(value = "指导老师姓名")
    private String teacherName;

    @ApiModelProperty(value = "立项时间")
    private Date createTime;
}
