package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.dao.TeacherDAO;
import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

public class TeacherServiceImplJdbc implements TeacherService {

    private TeacherDAO teacherDAO;

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        return teachers;
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        return -1;
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        List<Teacher> teachers = new ArrayList<>();
        return teachers;
    }

    public void updateTeacher(Teacher teacher) {
    }

    public void deleteTeacher(int teacherId) {
    }

    public Teacher getTeacherById(int teacherId) {
        return null;
    }
}