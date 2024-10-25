package com.szublog.service.impl;

import com.szublog.mapper.UserMapper;
import com.szublog.pojo.User;
import com.szublog.service.UserService;
import com.szublog.util.Md5Util;
import com.szublog.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密密码
        String md5String= Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> m= ThreadLocalUtil.get();
        Integer id= (Integer) m.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> m= ThreadLocalUtil.get();
        Integer id= (Integer) m.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
