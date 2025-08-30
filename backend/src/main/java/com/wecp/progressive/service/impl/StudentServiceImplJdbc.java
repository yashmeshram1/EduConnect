package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.dao.StudentDAO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;

public class StudentServiceImplJdbc implements StudentService {

    private StudentDAO studentDAO;

    public StudentServiceImplJdbc(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>();
    }

    @Override
    public Integer addStudent(Student student) {
        return -1;
    }

    @Override
    public List<Student> getAllStudentSortedByName() {
        return new ArrayList<>();
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(int studentId) {

    }

    @Override
    public Student getStudentById(int studentId) {
        return null;
    }
}