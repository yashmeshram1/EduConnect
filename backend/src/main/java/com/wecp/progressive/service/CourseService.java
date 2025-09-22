package com.wecp.progressive.service;

import com.wecp.progressive.entity.Course;

import java.util.List;

public interface CourseService {

    public List<Course> getAllCourses() throws Exception;

    public Course getCourseById(int courseId) throws Exception;

    public Integer addCourse(Course course) throws Exception;

    public void updateCourse(Course course) throws Exception;

    public void deleteCourse(int courseId) throws Exception;

    //Do not implement these methods in CourseServiceImplJdbc.java class
    default public List<Course> getAllCourseByTeacherId(int teacherId) { return null; }
}