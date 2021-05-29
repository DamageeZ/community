package com.mapsiz.dev.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: DamageeZ
 * @Create: 05-29-2021 0:58
 */
@Controller
public class IndexContorller {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
