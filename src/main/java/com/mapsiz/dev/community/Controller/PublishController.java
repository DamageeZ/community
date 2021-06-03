package com.mapsiz.dev.community.Controller;

import com.mapsiz.dev.community.Model.User;
import com.mapsiz.dev.community.Service.QuestionService;
import com.mapsiz.dev.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: DamageeZ
 * @Create: 06-02-2021 1:02
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @GetMapping("/publish")
    public String publish(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("tag") String tag,
                            @RequestParam("description") String description,
                            HttpServletRequest request,
                            Model model) {
        if(title == null || title.equals("")) {
            model.addAttribute("error","No Title");
            return "publish";
        }
        if(tag == null || tag.equals("")) {
            model.addAttribute("error","No tag");
            return "publish";
        }
        if(description == null || description.equals("")) {
            model.addAttribute("error","No description");
            return "publish";
        }
        User user = (User) request.getSession().getAttribute("user");
        if(user.getAccountId() == null) {
            model.addAttribute("error","Ooops, UserInfo is missed. Please Login again");
            return "index";
        }
        questionService.publishQuestion(tag,title,description,user);
        return "redirect:/happy?reason=Publish";
    }
}
