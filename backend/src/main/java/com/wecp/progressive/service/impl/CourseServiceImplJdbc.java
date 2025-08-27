package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.dao.CourseDAO;
import com.wecp.progressive.entity.Course;
import com.wecp.progressive.service.CourseService;

public class CourseServiceImplJdbc implements CourseService {

    private CourseDAO courseDAO;

    @Override
    public List<Course> getAllCourses() {
       
        List<Course> courses=new ArrayList<>();
        return courses;
    }

    @Override
    public Course getCourseById(int courseId) {
        return null;
    }

    @Override
    public Integer addCourse(Course course) {
        return -1;
    }

    @Override
    public void updateCourse(Course course) {
        
    }

    @Override
    public void deleteCourse(int courseId) {
        
    }

    

}