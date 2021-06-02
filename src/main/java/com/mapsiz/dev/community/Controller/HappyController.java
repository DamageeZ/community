package com.mapsiz.dev.community.Controller;

import com.mapsiz.dev.community.Model.User;
import com.mapsiz.dev.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: DamageeZ
 * @Create: 06-03-2021 1:34
 */
@Controller
public class HappyController {
    @Autowired
    private UserService userService;

    @GetMapping("/happy")
    public String happy(@RequestParam("reason") String reason,
                        Model model,
                        HttpServletRequest request) {
        model.addAttribute("reason",reason);
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
        return "happy";
    }
}
