package com.dreamer.demoproject.interceptors;

import com.dreamer.demoproject.util.JwtUtil;
import com.dreamer.demoproject.util.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component//将拦截器注入到IOC容器
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证
        String token=request.getHeader("Authorization");
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //将解析后的数据存储在ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        }
        catch (Exception e){
            //响应码401
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //情况ThreadLocal中的内容，放置内存泄露
        ThreadLocalUtil.remove();
    }
}
