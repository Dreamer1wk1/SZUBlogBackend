package com.dreamer.demoproject.controller;

import com.dreamer.demoproject.pojo.Result;
import com.dreamer.demoproject.pojo.User;
import com.dreamer.demoproject.service.UserService;
import com.dreamer.demoproject.util.JwtUtil;
import com.dreamer.demoproject.util.Md5Util;
import com.dreamer.demoproject.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户名是否重复
        User u = userService.findByUserName(username);
        if (u == null) {
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被使用");
        }
    }
    //登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询密码
        User u = userService.findByUserName(username);
        if (u == null) {
            return Result.error("用户不存在");
        }
        if (Md5Util.getMD5String(password).equals(u.getPassword())) {
            //登录成功
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            String token= JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    //查询信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        //根据用户名查询用户
        Map<String,Object>map=ThreadLocalUtil.get();
        User u=userService.findByUserName((String)map.get("username"));
        return Result.success(u);
    }
    //更新信息
    @PutMapping("/update")
    //PUT 请求通常用于更新现有资源的内容或状态
    //POST 请求通常用于创建新资源或提交数据
    //@RequestBody 注解表示该方法接受的参数类型是从 HTTP 请求的 body 中传递过来的 JSON 数据，并将其转换为方法参数的类型
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    //更新头像
    @PatchMapping("/updateAvatar")//与 PUT 请求不同，PATCH 请求仅对资源的一部分进行修改，而不是替换整个资源
    public Result updateAvatar(@RequestParam String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    //更新密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String>params){
        //校验参数
        String oldPwd=params.get("old_pwd");
        String newPwd=params.get("new_pwd");
        String rePwd=params.get("re_pwd");
        if(!StringUtils.hasLength(oldPwd)||!StringUtils.hasLength(rePwd)||!StringUtils.hasLength(newPwd)){
            return Result.error("缺少必要参数");
        }
        //校验源密码
        Map<String,Object>map=ThreadLocalUtil.get();
        String username=(String)map.get("username");
        User loginUser=userService.findByUserName(username);
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }
        if (!newPwd.matches("^\\S{5,16}$")) {
            return Result.error("新密码格式不正确");
        }
        if(!rePwd.equals(newPwd)){
            return Result.error("请输入相同的密码");
        }
        //调用service完成更新
        userService.updatePwd(newPwd);
        return Result.success();
    }
}

