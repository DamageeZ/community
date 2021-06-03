package com.mapsiz.dev.community.Service;

import com.alibaba.fastjson.JSON;
import com.mapsiz.dev.community.DTO.AccessTokenDTO;
import com.mapsiz.dev.community.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * @Author: DamageeZ
 * @Create: 05-31-2021 17:27
 */
@Component
public class GiteeService {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://gitee.com/oauth/token?grant_type=authorization_code")
                .header("Accept","application/json;charset=utf-8")
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser User = JSON.parseObject(string,GithubUser.class);
            return User.getAccess_token();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://gitee.com/api/v5/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
