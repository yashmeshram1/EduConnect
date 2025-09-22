package com.wecp.progressive.service;

import com.wecp.progressive.entity.Attendance;

import java.util.List;

public interface AttendanceService {

    List<Attendance> getAllAttendance() throws Exception;

    Attendance createAttendance(Attendance attendance) throws Exception;

    void deleteAttendance(int attendanceId) throws Exception;

    List<Attendance> getAttendanceByStudent(int studentId) throws Exception;

    List<Attendance> getAttendanceByCourse(int courseId) throws Exception;
}