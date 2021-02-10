package com.team.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhaohao
 * @date 2021/1/5 10:02
 * @param 
 * @return 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
@ApiModel("用户类")
public class User {

  @ApiModelProperty(value = "主键")
  @TableId(value = "id",type = IdType.AUTO)
  private Integer id;

  @ApiModelProperty(value = "用户标示")
  private String userId;

  @ApiModelProperty(value = "用户姓名")
  private String userName;

  @ApiModelProperty(value = "用户密码")
  private String userPassword;

  @ApiModelProperty(value = "用户电话")
  private String userTel;

  @ApiModelProperty(value = "用户邮箱")
  private String userEmail;

  @ApiModelProperty(value = "权限 0为管理员 1为教师 2为学生")
  private Integer authority;

  @ApiModelProperty(value = "创建时间")
  private java.sql.Timestamp createTime;
  
  @ApiModelProperty(value = "更新时间")
  private java.sql.Timestamp updateTime;
}
