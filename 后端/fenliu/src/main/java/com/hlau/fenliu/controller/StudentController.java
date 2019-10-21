package com.hlau.fenliu.controller;

import com.hlau.fenliu.Utils.RestUtil;
import com.hlau.fenliu.entity.Aspirations;
import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;
@RestController
@Secured({"ROLE_STUDENT"})
public class StudentController {
    @Autowired
    UserService userService;
    @PostMapping("/reviseAspiration")
    public LinkedHashMap reviseAspiration(Principal principal, @RequestBody @Valid Aspirations aspirations) {
        User user = userService.findByUserId(principal.getName());
        user.setAspirations(aspirations.getAspirations());
        return RestUtil.SUCCESS(userService.reviseAspiration(user));
    }
    @PostMapping("/subscribe")
    public LinkedHashMap subscribe(Principal principal, @RequestBody User u){
        User user = userService.findByUserId(principal.getName());
        user.setEmail(u.getEmail());
        user.setSubscribe(u.getSubscribe());
        return RestUtil.SUCCESS(userService.subscribe(user));
    }
}
