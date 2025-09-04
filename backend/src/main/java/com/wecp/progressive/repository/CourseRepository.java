
package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Course;
import java.util.List;


public interface CourseRepository extends JpaRepository<Course, Integer>{
    // public Course findByCourseId(Integer courseId);
    // public Course findByCourseName(String courseName);
    // public List<Course> findAllByTeacherId(Integer teacherId);
    // public void deleteByTeacherId(Integer teacherId);
}
