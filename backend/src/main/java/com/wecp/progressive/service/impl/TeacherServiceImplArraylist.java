package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wecp.progressive.entity.Teacher;
import com.wecp.progressive.service.TeacherService;

public class TeacherServiceImplArraylist implements TeacherService {

    private static List<Teacher> teacherList=new ArrayList<>();

    @Override
    public List<Teacher> getAllTeachers() {
        // List<Teacher> teachers = new ArrayList<>();
        return teacherList;
    }

    @Override
    public Integer addTeacher(Teacher teacher) {
        teacherList.add(teacher);
        return teacherList.size();
    }

    @Override
    public List<Teacher> getTeacherSortedByExperience() {
        List<Teacher> localteachers = new ArrayList<>(teacherList);
        Collections.sort(localteachers);
        return localteachers;
    }

    @Override
    public void emptyArrayList() 
    {
        teacherList.clear();
    }

    public Comparator yearofExper=new Comparator<Teacher>() {

        @Override
        public int compare(Teacher t1, Teacher t2) {
           return Integer.compare(t1.getYearsOfExperience(), t2.getYearsOfExperience());
        }
        
        
    };

}