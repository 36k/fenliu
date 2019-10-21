package com.hlau.fenliu.repository;

import com.hlau.fenliu.entity.Department;
import com.hlau.fenliu.entity.Role;
import com.hlau.fenliu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserIdAndPassword(String userId, String password);
//    List<User> findAllByDepartmentOrderByScoreDesc(Department department);
    int countUserByDepartmentAndRole(Department department, Role role);
    List<User> findByDepartmentAndRoleOrderByScoreDesc(Department department, Role role);
    User findByUserId(String userId);
}
