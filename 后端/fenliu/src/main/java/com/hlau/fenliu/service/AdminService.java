package com.hlau.fenliu.service;

import com.hlau.fenliu.entity.Aspiration;
import com.hlau.fenliu.entity.Role;
import com.hlau.fenliu.entity.User;
import com.hlau.fenliu.repository.RoleRepository;
import com.hlau.fenliu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;
    @Autowired
    RoleRepository roleRepository;
    public  Integer count(User user){
        Role role=roleRepository.findByRoleId("STUDENT");
        Integer integer = userRepository.countUserByDepartmentAndRole(user.getDepartment(), role);
        return integer;
    }
    public List<User> fenliu(Map<String,Integer> majorMap, User user){
        Set<User> aspiration2=new LinkedHashSet<>();
        Set<User> aspiration3=new LinkedHashSet<>();
        Set<User> success=new LinkedHashSet<>();

        try {
            System.out.println("开始处理第一志愿");
            //第一志愿人数
            //Sort sort=new Sort(Sort.Direction.DESC,"score");
            Role role=roleRepository.findByRoleId("STUDENT");
            List<User> all = userRepository.findByDepartmentAndRoleOrderByScoreDesc(user.getDepartment(),role);
            for (User u:
                    all) {
                for (Aspiration aspiration :
                        u.getAspirations()) {
                    if(aspiration.getLevel()==1){
                        aspe(majorMap, aspiration2, success, u, aspiration);
                    }
                }
            }
            System.out.println("开始处理第二志愿");
            for (User u:
                    aspiration2) {
                for (Aspiration aspiration :
                        u.getAspirations()) {
                    if(aspiration.getLevel()==2){
                        aspe(majorMap, aspiration3, success, u, aspiration);
                    }
                }
            }
            System.out.println("开始处理第三志愿");
            for (User u:
                    aspiration3) {
                for (Aspiration aspiration :
                        u.getAspirations()) {
                    if (aspiration.getLevel() == 3) {
                        u.setMajor(aspiration.getMajor());
                        success.add(u);
                    }
                }
            }
            emailService.sendEmail(success);
            List<User> users = userRepository.saveAll(success);
            return users;
        } catch (Exception e) {
            System.out.println("邮件发送出错");
        }
        return null;
    }
    public List<User> studentInfo(User user){
        Role role=roleRepository.findByRoleId("STUDENT");
        List<User> users = userRepository.findByDepartmentAndRoleOrderByScoreDesc(user.getDepartment(), role);
        return users;
    }




    private void aspe(Map<String, Integer> majorMap, Set<User> aspirations, Set<User> success, User u, Aspiration aspiration) {
        int num=(majorMap.get(aspiration.getMajor().getMajorId()));
        if(num-1<0){
            aspirations.add(u);
        }else{
            majorMap.put(aspiration.getMajor().getMajorId(),num-1);
            u.setMajor(aspiration.getMajor());
            success.add(u);
        }
    }

}
