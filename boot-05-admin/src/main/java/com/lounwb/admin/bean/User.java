package com.lounwb.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user_tbl")
public class User {
    /**
     * 所有属性在数据库中都应该存在，如果不存在使用下面
     */
    @TableField(exist = false)

    private String username;
    @TableField(exist = false)
    private String password;

    //数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
