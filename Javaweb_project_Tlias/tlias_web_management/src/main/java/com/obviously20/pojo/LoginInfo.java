package com.obviously20.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    /**
     * 成功登录后返回的登录信息
     * */
    private Integer id;
    private String username;
    private String name;
    private String token;
}
