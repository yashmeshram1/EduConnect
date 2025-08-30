package com.wecp.progressive.dao;

import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.entity.Course;

public class CourseDAOImpl implements CourseDAO{

    @Override
    public int addCourse(Course course) {
        return -1;
    }

    @Override
    public Course getCourseById(int courseId) {
        return null;
    }

    @Override
    public void updateCourse(Course course) {
    }

    @Override
    public void deleteCourse(int courseId) {
        
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>();
    }

}
