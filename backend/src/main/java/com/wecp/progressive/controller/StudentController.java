package com.wecp.progressive.controller;

import com.wecp.progressive.dto.StudentDTO;
import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.impl.StudentServiceImplArraylist;
import com.wecp.progressive.service.impl.StudentServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImplJpa studentServiceJPA;

    @Autowired
    private StudentServiceImplArraylist studentServiceArrayList;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentServiceJPA.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId) {
        try {
            Student student = studentServiceJPA.getStudentById(studentId);
            if (student != null) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(student, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public ResponseEntity<Integer> addStudent(@RequestBody Student student) {
        try {
            Integer studentId = studentServiceJPA.addStudent(student);
            if (studentId != null) {
                return new ResponseEntity<>(studentId, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studentId, @RequestBody StudentDTO studentDTO) {
        try {
            studentDTO.setStudentId(studentId);
            // studentServiceJPA.updateStudent(student);
            studentServiceJPA.modifyStudentDetails(studentDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int studentId) {
        try {
            studentServiceJPA.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fromArrayList")
    public ResponseEntity<List<Student>> getAllStudentFromArrayList() {
        List<Student> students = studentServiceArrayList.getAllStudents();
        if (students.size() > 0) {
            return new ResponseEntity<>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(students, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addStudentToArrayList(@RequestBody Student student) {
        int studentsListSize = studentServiceArrayList.addStudent(student);
        return new ResponseEntity<>(studentsListSize, HttpStatus.CREATED);
    }

    @GetMapping("/fromArrayList/sorted")
    public ResponseEntity<List<Student>> getAllStudentSortedByNameFromArrayList() {
        List<Student> sortedStudentsByName = studentServiceArrayList.getAllStudentSortedByName();
        if (sortedStudentsByName.size() > 0) {
            return new ResponseEntity<>(sortedStudentsByName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(sortedStudentsByName, HttpStatus.NO_CONTENT);
        }
    }
}