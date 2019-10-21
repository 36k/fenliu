package com.hlau.fenliu.service;

import com.hlau.fenliu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Service
public class EmailService {
    @Autowired
    JavaMailSender jms;
    private ExecutorService executor = Executors.newCachedThreadPool() ;
    public  void sendEmail (Set<User> users)throws Exception{
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    for (User u:
                            users) {
                        if (u.getSubscribe()!=null&&u.getSubscribe()){
                            SimpleMailMessage mainMessage = new SimpleMailMessage();
                            mainMessage.setFrom("anyous@yeah.net");
                            //接收者
                            mainMessage.setTo(u.getEmail());
                            System.out.println("正在给"+u.getEmail()+"发送通知邮件");
                            //发送的标题
                            mainMessage.setSubject("分流结果出来啦");
                            //发送的内容
                            mainMessage.setText("您的专业为："+u.getMajor().getMajorName());
                            jms.send(mainMessage);
                        }
                    }
                }catch(Exception e){
                    System.out.println("邮件发送出错");
                }
            }
        });


    }
}
