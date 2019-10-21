package com.hlau.fenliu.service;

import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender jms;
    public User login(User user) {
        User user1 = userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword());
        return  user1;
    }
    public User reviseAspiration(User user){
        User user1 = userRepository.saveAndFlush(user);
        return user;
    }
    public User revisePassword(User user){
        User user1 = userRepository.saveAndFlush(user);
        return user;
    }
    public User subscribe(User user){
        return userRepository.saveAndFlush(user);
    }
    public User insertUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

}
