package com.hlau.fenliu;

import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.repository.UserRepository;
import com.hlau.fenliu.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class) //底层用junit SpringJUnit4ClassRunner
@SpringBootTest(classes = FenliuApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
   UserService userService;
    @Autowired
    UserRepository userRepository;
    @Test
    public void get(){
        User user=new User();
        User userId = userService.findByUserId("20164081112");
        userId.setPassword(new BCryptPasswordEncoder().encode("123456"));
        userRepository.saveAndFlush(userId);
    }
}
