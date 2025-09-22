package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.progressive.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

    // Optional<Enrollment> findByStudent_StudentIdAndCourse_CourseId(int studentId, int courseId);

    List<Enrollment> findAllByStudent_StudentId(int student_id);

    List<Enrollment> findAllByCourse_CourseId(int course_id);

    // void deleteByCourseId(int courseId);

    // void deleteByStudentId(int studentId);

    // void deleteByTeacherId(int teacherId);
}
