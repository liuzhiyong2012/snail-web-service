package com.snail.web.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/*@Data
public class User {
    private String id;
    private String name;
    private Integer sex;
    private String phone;
}*/

@Data
@TableName("user")
public class User implements Serializable {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String username;
    private String name;
    private String password;
    @TableField("created_by")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("created_time")
    private Date createdTime;
    @TableField("updated_by")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("updated_time")
    private Date updatedTime;
    @TableField("role_id")
    private String roleId;
}

/*public class User {
 *//*   @TableId*//*
    public String id;
    public String name;
    public int sex;
    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}*/
