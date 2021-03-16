package com.team.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author liuzhaohao
 * @date 2021/3/16 11:14 上午
 * @param
 * @return
 */

@Data
@ApiModel("论文")
@TableName(value = "paper")
@RequiredArgsConstructor
public class Paper {

    @ApiModelProperty
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("接收人")
    @NonNull private String userId;

    @ApiModelProperty("论文文件名")
    @NonNull private String fileName;

}
