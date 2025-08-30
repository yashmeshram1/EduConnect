package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.dao.TeacherDAO;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

public class TeacherServiceImplJdbc implements TeacherService {

    private TeacherDAO teacherDAO;

    public TeacherServiceImplJdbc(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>();
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        return -1;
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        return new ArrayList<>();
    }

    public void updateTeacher(Teacher teacher) {

    }

    public void deleteTeacher(int teacherId) {

    }

    public Teacher getTeacherById(int teacherId) {
        return null;
    }

}