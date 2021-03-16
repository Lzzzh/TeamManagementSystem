package com.team.wechatservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("消息主体")
@TableName("message")
public class Message {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("消息ID")
    private String messageId;

    @ApiModelProperty("收件人")
    private String receiverId;

    @ApiModelProperty("发件人")
    private String senderId;

    @ApiModelProperty("发件人姓名")
    private String senderName;

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("消息状态，0为未读，1为已读，2为回收站")
    private Integer status;

    @ApiModelProperty("时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
