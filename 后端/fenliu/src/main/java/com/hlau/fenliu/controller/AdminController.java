package com.hlau.fenliu.controller;

import com.hlau.fenliu.Utils.RestUtil;
import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.service.AdminService;
import com.hlau.fenliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    JavaMailSender jms;
    @Autowired
    UserService userService;
    @PostMapping("/fenliu")
    public LinkedHashMap fenliu(@RequestBody Map<String,Integer> majorMap, Principal principal){
        User user = userService.findByUserId(principal.getName());
        return RestUtil.SUCCESS(adminService.fenliu(majorMap, user));
    }
    @GetMapping("/count")
    public LinkedHashMap count(Principal principal){
        User user = userService.findByUserId(principal.getName());
        int count = adminService.count(user);
      return RestUtil.SUCCESS(count);
    }
    @GetMapping("/studentInfo")
    public LinkedHashMap studentInfo(Principal principal){
        User user = userService.findByUserId(principal.getName());
        return RestUtil.SUCCESS(adminService.studentInfo(user));
    }

}
