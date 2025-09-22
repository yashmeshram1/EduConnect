package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    public Teacher findByTeacherId(int teacherId);
    public Teacher findByEmail(String email);
}