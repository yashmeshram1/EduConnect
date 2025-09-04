package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Course;
import com.wecp.progressive.exception.CourseAlreadyExistsException;
import com.wecp.progressive.exception.CourseNotFoundException;
import com.wecp.progressive.repository.CourseRepository;
import com.wecp.progressive.repository.TeacherRepository;
import com.wecp.progressive.service.CourseService;

@Service
public class CourseServiceImplJpa implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    public CourseServiceImplJpa(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public CourseServiceImplJpa(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseServiceImplJpa(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Course> getAllCourses() throws Exception {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int courseId) throws Exception {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        return course;
    }

    @Override
    public Integer addCourse(Course course) throws Exception {
        List<Course> courses = courseRepository.findAll();
        for (Course c : courses) {
            if (c.getCourseName().equals(course.getCourseName())) {
                throw new CourseAlreadyExistsException("Course already exists");
            }
        }
        Course newCourse = courseRepository.save(course);
        return newCourse.getCourseId();
    }

    @Override
    public void updateCourse(Course course) throws Exception {
        Course updatedCourse = courseRepository.findById(course.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException());
                
        updatedCourse.setCourseName(course.getCourseName());
        updatedCourse.setDescription(course.getDescription());
        updatedCourse.setTeacher(course.getTeacher());
        courseRepository.save(updatedCourse);
    }

    @Override
    public void deleteCourse(int courseId) throws Exception {
        Course deletedCourse = courseRepository.findById(courseId).orElse(null);
        if (deletedCourse != null) {
            courseRepository.delete(deletedCourse);
        }
    }

    public List<Course> getAllCourseByTeacherId(int teacherId) {
        List<Course> courses = courseRepository.findAll();
        List<Course> coursesByTeacher = new ArrayList<>();
        for (Course c : courses) {
            if (c.getTeacher().getTeacherId() == teacherId) {
                coursesByTeacher.add(c);
            }
        }
        return coursesByTeacher;
    }
}