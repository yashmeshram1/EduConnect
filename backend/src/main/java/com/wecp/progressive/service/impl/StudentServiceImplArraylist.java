package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wecp.progressive.entity.Student;
import com.wecp.progressive.service.StudentService;

public class StudentServiceImplArraylist implements StudentService {

    private static List<Student> studentList=new ArrayList<>();
    @Override
    public List<Student> getAllStudents() {
        // List<Student> students=new ArrayList<>();

        return studentList;
    }

    @Override
    public Integer addStudent(Student student) {
        studentList.add(student);
        return studentList.size();
    }

    @Override
    public List<Student> getAllStudentSortedByName() {
        List<Student> localstudents=new ArrayList<>(studentList);
        Collections.sort(localstudents);
        return localstudents;
    }
    @Override
    public void emptyArrayList() 
    {
        studentList.clear();
    }

    public Comparator nammeComparator=new Comparator<Student>() {

        @Override
        public int compare(Student s1, Student s2) {
            return s1.getFullName().compareTo(s2.getFullName());
        }
        
        
    };

}