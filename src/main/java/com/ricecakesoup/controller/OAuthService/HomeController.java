package com.ricecakesoup.controller.OAuthService;

import com.ricecakesoup.service.OAuthService.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @Autowired
    private OAuthService kakao;

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/user/kakao/callback")
    public String login(@RequestParam("code") String code) {
        String access_Token = kakao.getKakaoAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        return "index";
    }
}

