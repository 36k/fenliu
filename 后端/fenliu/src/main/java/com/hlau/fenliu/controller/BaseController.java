package com.hlau.fenliu.controller;

import com.hlau.fenliu.Utils.RestUtil;
import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.LinkedHashMap;
@RestController
public class BaseController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @GetMapping("/info")
    public LinkedHashMap getUserInfo(Principal principal) {
        User user = userService.findByUserId(principal.getName());
        return  RestUtil.SUCCESS(user);
    }
    @PostMapping("revisePassword")
    public LinkedHashMap revisePassword(Principal principal ,@RequestBody User u){
        User user = userService.findByUserId(principal.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
        return RestUtil.SUCCESS(userService.revisePassword(user));
    }
}
