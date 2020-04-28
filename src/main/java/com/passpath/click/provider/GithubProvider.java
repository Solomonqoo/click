package com.passpath.click.provider;

import com.alibaba.fastjson.JSON;
import com.passpath.click.dto.AccessTokenDTO;
import com.passpath.click.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();  //先用"&"拆分，再用"="拆分
//			String[] split = string.split("&");
//			String tokenString = split[0];
//			String token = tokenString.split("=")[1];
//			System.out.println(token);
            //改造一下代碼
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken) {   //通過String類型的access_token
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+ accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);      //可以把string直接轉換成一個java的類對象
            return githubUser;			//把string的json對象，去自動的轉換成java 的類對象(指轉成GithubUser)，不用自已去解析
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
