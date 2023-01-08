package com.ricecakesoup.controller.OAuthService;

import com.ricecakesoup.service.OAuthService.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class HomeController {
    @Autowired
    private OAuthService kakao;

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/user/kakao/callback")
    public String login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getKakaoAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            System.out.println("클라이언트 이메일 존재");
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
        return "index";
    }
}

