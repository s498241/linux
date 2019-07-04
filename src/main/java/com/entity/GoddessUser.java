package com.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by liuliang on 2018/11/1.
 */
@TableName("goddess_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "wantu_notice_info", type = "doc")
public class GoddessUser {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @TableField("user_name")
    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("phone")
    private String phone;
}