package com.mapsiz.dev.community.Controller;

import com.mapsiz.dev.community.DTO.QuestionDTO;
import com.mapsiz.dev.community.Model.User;
import com.mapsiz.dev.community.Service.QuestionService;
import com.mapsiz.dev.community.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;

/**
 * @Author: DamageeZ
 * @Create: 05-29-2021 0:58
 */
@Controller
public class IndexContorller {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        User user = new User();
        Random r = new Random();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userService.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        byte[] nbytes = new byte[5];
        r.nextBytes(nbytes);
        model.addAttribute("state", DigestUtils.md5DigestAsHex(nbytes));
        request.getSession().setAttribute("state", DigestUtils.md5DigestAsHex(nbytes));

        List<QuestionDTO> questionDTOList = questionService.listQuestions();
        model.addAttribute("questions",questionDTOList);
        return "index";
    }
}
