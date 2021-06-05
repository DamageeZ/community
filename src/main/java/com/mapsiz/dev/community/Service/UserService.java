package com.mapsiz.dev.community.Service;

import com.mapsiz.dev.community.DTO.GithubUser;
import com.mapsiz.dev.community.Mapper.UserMapper;
import com.mapsiz.dev.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author: DamageeZ
 * @Create: 06-01-2021 20:25
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String createUser(GithubUser githubUser) {
        User user = new User();
        user.setToken(UUID.randomUUID().toString());
        user.setName(githubUser.getName());
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setAccountId(String.valueOf(githubUser.getId()));
        user.setAvatarUrl(githubUser.getAvatar_url());
        if (userMapper.findByAccountId(user.getAccountId()) == null) {
            userMapper.insert(user);
        } else {
            userMapper.updateTokenById(user.getAccountId(),user.getToken(),System.currentTimeMillis());
        }
        return user.getToken();
    }

    public User findByToken(String token) {
        User user = userMapper.findByToken(token);
        return user;
    }

    public String updateTokenById(String token,String id) {
        userMapper.updateTokenById(id,token,System.currentTimeMillis());
        return token;
    }
}
