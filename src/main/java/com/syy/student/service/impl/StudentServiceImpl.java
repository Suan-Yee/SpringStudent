package com.syy.student.service.impl;

import com.syy.student.dto.StudentDto;
import com.syy.student.entity.Student;
import com.syy.student.exception.EmailAlreadyExistsException;
import com.syy.student.exception.ResourceNotFoundException;
import com.syy.student.mapper.UserMapper;
import com.syy.student.repository.StudentRepository;
import com.syy.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        // Student student1 = UserMapper.mapToStudent(studentDto);
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());

        if(optionalStudent.isPresent()){
            throw new EmailAlreadyExistsException("The User Email is already Exists");
        }

        Student student1 = modelMapper.map(studentDto,Student.class);

        Student savedStudent = studentRepository.save(student1);

        //StudentDto savedStudentDto = UserMapper.mapToStudentDto(savedStudent);
        StudentDto savedStudentDto = modelMapper.map(savedStudent,StudentDto.class);

        return savedStudentDto;
    }

    @Override
    public List<StudentDto> getAllStudent() {

        List<Student> allStudent = studentRepository.findAll();
//        return allStudent.stream().map(UserMapper::mapToStudentDto)
//                .collect(Collectors.toList());

        return allStudent.stream().map((student) -> modelMapper.map(student,StudentDto.class) )
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto getStudentByID(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student","id",studentId)
        );
        //Student savedStudent = student.get();
        //StudentDto savedStudentDto = UserMapper.mapToStudentDto(savedStudent);
        StudentDto savedStudentDto = modelMapper.map(student,StudentDto.class);
        return savedStudentDto;
    }

    @Override
    public StudentDto updateStudent(StudentDto student) {
       Student existingStudent =  studentRepository.findById(student.getId()).orElseThrow(
               () -> new ResourceNotFoundException("User","id",student.getId())
       );
       existingStudent.setName(student.getName());
       existingStudent.setAge(student.getAge());
       existingStudent.setGrade(student.getGrade());
       existingStudent.setEmail(student.getEmail());

       Student updatedStudent = studentRepository.save(existingStudent);
        //return UserMapper.mapToStudentDto(updatedStudent);
        return modelMapper.map(updatedStudent,StudentDto.class);
    }

    @Override
    public void deleteStudent(Long studentId) {

        studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("User","id",studentId)
        );
        studentRepository.deleteById(studentId);
    }
}
