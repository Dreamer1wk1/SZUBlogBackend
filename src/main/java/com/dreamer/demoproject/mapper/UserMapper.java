package com.dreamer.demoproject.mapper;

import com.dreamer.demoproject.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //查询用户
    @Select("select * from user where username =#{username}")
    User findByUserName(String username);

    //注册
    @Insert("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{md5String},now(),now())")
    void add(String username, String md5String);

    //更新信息
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //更新头像
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    //更新密码
    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
