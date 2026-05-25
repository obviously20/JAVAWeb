package com.obviously20.controller;

import com.obviously20.pojo.Emp;
import com.obviously20.pojo.LoginInfo;
import com.obviously20.pojo.Result;
import com.obviously20.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录接口
     * */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){

        //记录登录信息日志
        log.info("登录信息username:{} password:{}", emp.getUsername(), emp.getPassword());

        //调用服务层方法进行登录验证
        LoginInfo loginInfo = empService.login(emp);
        //如果返回值不为空的，说明登录成功
        if(loginInfo != null){
            return Result.success(loginInfo);
        }

        //返回值为空的说明登录失败，返回登录失败信息
        return Result.error("用户名或密码错误");
    }


}
