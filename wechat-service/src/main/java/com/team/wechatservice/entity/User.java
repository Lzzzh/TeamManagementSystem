package com.team.wechatservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户类")
public class User {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "用户标识")
  private String userId;

  @ApiModelProperty(value = "用户姓名")
  private String userName;


  @ApiModelProperty(value = "用户密码")
  private String userPassword;

  @ApiModelProperty(value = "用户电话")
  private String userTel;

  @ApiModelProperty(value = "用户邮箱")
  private String userEmail;

  @ApiModelProperty(value = "权限 0为学生 1为教师 2为管理员")
  private Integer authority;

  @ApiModelProperty(value = "上次登录时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date lastLoginTime;
}
