package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String userName);
    // User findByTeacherId(int teacherId);
    // User findByStudentId(int studentId);
    // void deleteByTeacherId(int teacherId);
    // void deleteByStudentId(int studentId);
}
