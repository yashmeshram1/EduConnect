package com.wecp.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.exception.StudentAlreadyExistsException;
import com.wecp.progressive.repository.StudentRepository;
import com.wecp.progressive.service.StudentService;

@Service
public class StudentServiceImplJpa implements StudentService {

    StudentRepository studentRepository;

    public StudentServiceImplJpa(StudentRepository studentRepository) throws Exception {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        return studentRepository.findAll();
    }

    @Override
    public Integer addStudent(Student student) throws Exception {
        List<Student> students = studentRepository.findAll();
        for (Student s : students) {
            if (s.getFullName().equals(student.getFullName())) {
                throw new StudentAlreadyExistsException("Student already exists");
            }
        }
        Student newStudent = studentRepository.save(student);
        return newStudent.getStudentId();
    }

    @Override
    public List<Student> getAllStudentSortedByName() throws Exception {
        List<Student> sortedStudents = studentRepository.findAll();
        Collections.sort(sortedStudents);
        return sortedStudents;
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        Student updatedStudent = studentRepository.findById(student.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist"));
                
        updatedStudent.setFullName(student.getFullName());
        updatedStudent.setEmail(student.getEmail());
        updatedStudent.setContactNumber(student.getContactNumber());
        updatedStudent.setDateOfBirth(student.getDateOfBirth());
        updatedStudent.setAddress(student.getAddress());
        studentRepository.save(updatedStudent);
    }

    @Override
    public void deleteStudent(int studentId) throws Exception {
        Student deletedStudent = studentRepository.findById(studentId).orElse(null);
        if (deletedStudent != null) {
            studentRepository.delete(deletedStudent);
        }
    }

    @Override
    public Student getStudentById(int studentId) throws Exception {
        return studentRepository.findById(studentId).orElse(null);
    }

    @Override
    public void modifyStudentDetails(StudentDTO studentDTO) {
        Student updatedStudent = studentRepository.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student does not exist"));
                
        updatedStudent.setFullName(studentDTO.getFullName());
        updatedStudent.setEmail(studentDTO.getEmail());
        updatedStudent.setContactNumber(studentDTO.getContactNumber());
        updatedStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        updatedStudent.setAddress(studentDTO.getAddress());
        studentRepository.save(updatedStudent);
    }

}