package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Student findByStudentId(int studentId);
    public Student findByEmail(String email);
}