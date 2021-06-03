package com.mapsiz.dev.community.Controller;

import com.mapsiz.dev.community.DTO.AccessTokenDTO;
import com.mapsiz.dev.community.DTO.GithubUser;
import com.mapsiz.dev.community.Service.GithubService;
import com.mapsiz.dev.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: DamageeZ
 * @Create: 05-31-2021 17:18
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubService githubService;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect}")
    private String redirectUri;
    @Value("${github.client.secret}")
    private String clientSecret;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        String accessToken = githubService.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubService.getUser(accessToken);
        if (githubUser != null && githubUser.getId()!= null) {
            String token = userService.createUser(githubUser);
            response.addCookie(new Cookie("token", token));
            System.out.println(githubUser.getName());
            return "redirect:/";
        } else {
        }

        return "index";
    }
}
