package com.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * <p>
 * 
 * </p>
 *
 * @author ss
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_info")
@Document(indexName = "wantu_notice_info", type = "doc")
public class UserInfo extends Model<UserInfo> {

private static final long serialVersionUID=1L;


    @TableId("userId")
    @Id
    private Long userId;

    @TableField("userName")
    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("sex")
    private int sex;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
