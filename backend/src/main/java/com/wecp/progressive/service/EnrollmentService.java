
package com.wecp.progressive.service;

import com.wecp.progressive.entity.Enrollment;

import java.text.ParseException;
import java.util.List;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments() throws ParseException;

    int createEnrollment(Enrollment enrollment) throws Exception;

    public void updateEnrollment(Enrollment enrollment) throws Exception;

    public Enrollment getEnrollmentById(int enrollmentId)  throws ParseException;

    public List<Enrollment> getAllEnrollmentsByStudent(int studentId) throws Exception;

    public List<Enrollment> getAllEnrollmentsByCourse(int courseId) throws Exception;
}
