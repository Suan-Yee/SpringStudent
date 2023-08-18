package com.syy.student.service;

import com.syy.student.dto.StudentDto;
import com.syy.student.entity.Student;

import java.util.List;

public interface StudentService {

    StudentDto createStudent(StudentDto student);

    List<StudentDto> getAllStudent();

    StudentDto getStudentByID(Long studentId);

    StudentDto updateStudent(StudentDto student);

    void deleteStudent(Long studentId);
}
