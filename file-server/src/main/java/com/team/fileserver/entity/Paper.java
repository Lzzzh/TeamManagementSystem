package com.team.fileserver.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("论文文件实体类")
public class Paper {

    @ApiModelProperty("主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("收件人名")
    private String userId;

    @ApiModelProperty("文件名")
    private String fileName;
}
