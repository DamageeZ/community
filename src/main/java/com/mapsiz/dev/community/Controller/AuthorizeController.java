package com.mapsiz.dev.community.Controller;

import com.mapsiz.dev.community.DTO.AccessTokenDTO;
import com.mapsiz.dev.community.DTO.GithubUser;
import com.mapsiz.dev.community.Service.GiteeService;
import com.mapsiz.dev.community.Service.GithubService;
import com.mapsiz.dev.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
    private String GHClientId;
    @Value("${github.redirect}")
    private String GHRedirectUri;
    @Value("${github.client.secret}")
    private String GHClientSecret;
    @Value("${gitee.client.id}")
    private String GEClientId;
    @Value("${gitee.redirect}")
    private String GERedirectUri;
    @Value("${gitee.client.secret}")
    private String GEClientSecret;

    @Autowired
    private UserService userService;

    @Autowired
    private GiteeService giteeService;

    @GetMapping("/GHCallback")
    public String GHCallback(@RequestParam(name = "code") String code,
                             @RequestParam(name = "state") String state,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_secret(GHClientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(GHRedirectUri);
        accessTokenDTO.setClient_id(GHClientId);
        String accessToken = githubService.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubService.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            String token = userService.createUser(githubUser);
            response.addCookie(new Cookie("token", token));
            System.out.println(githubUser.getName());
            return "redirect:/";
        } else {
        }

        return "index";
    }

    @GetMapping("/GECallback")
    public String GECallback(@RequestParam(name = "code") String code,
                             @RequestParam(name = "state") String state,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        String oldState = (String) request.getSession().getAttribute("state");
        if (oldState.equals(state)) {
            accessTokenDTO.setCode(code);
            accessTokenDTO.setClient_secret(GEClientSecret);
            accessTokenDTO.setState(state);
            accessTokenDTO.setRedirect_uri(GERedirectUri);
            accessTokenDTO.setClient_id(GEClientId);
            String accessToken = giteeService.getAccessToken(accessTokenDTO);
            GithubUser githubUser = giteeService.getUser(accessToken);
            if (githubUser != null && githubUser.getId() != null) {
                String token = userService.createUser(githubUser);
                response.addCookie(new Cookie("token", token));
                System.out.println(githubUser.getName());
                return "redirect:/";
            } else {
            }
        }

        return "index";
    }
}
