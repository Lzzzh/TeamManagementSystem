package com.team.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhaohao
 * @date 2021/1/3 9:57 下午
 * @param
 * @return
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private long id;
  private String userId;
  private String userName;
  private String userPassword;
  private String userTel;
  private String userEmail;
  private long authority;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;

}
