package com.example.controller;


import com.example.pojo.Emp;
import com.example.pojo.LoginInfo;
import com.example.pojo.Result;
import com.example.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private EmpService empService;

    //根据用户名与密码查询员工信息
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录请求: " + emp);
        LoginInfo logininfo = empService.login(emp);
        if (logininfo != null) {
            log.info("登录成功: " + logininfo);
            return Result.success(logininfo);
        } else {
            log.warn("登录失败: " + emp);
            return Result.error("用户名或密码错误");
        }

    }
}
