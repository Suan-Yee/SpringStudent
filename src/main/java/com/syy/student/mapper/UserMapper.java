package com.syy.student.mapper;

import com.syy.student.dto.StudentDto;
import com.syy.student.entity.Student;

public class UserMapper {

    //convert UserJpa into UserDto
    public static StudentDto mapToStudentDto(Student student){

        StudentDto studentDto = new StudentDto(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGrade(),
                student.getEmail()
        );
        return studentDto;
    }
    //convert UserDto into UserJpa
    public static Student mapToStudent(StudentDto studentDto){

        Student student = new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getAge(),
                studentDto.getGrade(),
                studentDto.getEmail()
        );
        return student;
    }
}
