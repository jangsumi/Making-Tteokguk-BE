package com.ricecakesoup.controller.OAuthService;

import com.ricecakesoup.service.OAuthService.OAuthService;
import com.ricecakesoup.service.OAuthService.dto.response.LoginResDto;
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
    public LoginResDto login(@RequestParam("code") String code, HttpSession session) {
        String access_Token = kakao.getKakaoAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        String response_body="";
        String kakao_id = "";
        if (userInfo.get("email") == null) {
            System.out.println("null");
        }
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            System.out.println("클라이언트 이메일 존재");
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
            response_body = String.valueOf(userInfo.get("response_body"));

            String target = "\"id\"";
            int target_num = response_body.indexOf(target);
//            System.out.println(target_num);
            kakao_id = response_body.substring(6,(response_body.substring(target_num).indexOf(",")+1));
            System.out.println("kakaoid: " + kakao_id);
        }
        return LoginResDto.of(kakao_id);
    }
}

