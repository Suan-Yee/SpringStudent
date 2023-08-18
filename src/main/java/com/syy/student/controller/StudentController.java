package com.syy.student.controller;

import com.syy.student.dto.StudentDto;
import com.syy.student.exception.ErrorDetails;
import com.syy.student.exception.ResourceNotFoundException;
import com.syy.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/student")
@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
public class StudentController {

    private StudentService studentService;

    @Operation(
            summary = "Create Student REST API",
            description = "Create Student REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )

    @PostMapping
    public ResponseEntity<StudentDto> createStudent( @Valid @RequestBody StudentDto student){
       StudentDto createStudent = studentService.createStudent(student);
       return new ResponseEntity<>(createStudent,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Students ",
            description = "Get All Student REST API is used to get every student from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        List<StudentDto> allStudent = studentService.getAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @Operation(
            summary = "Get Student By ID REST API",
            description = "Get Student By ID REST API is used to get a single student from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long studentId){
        StudentDto student = studentService.getStudentByID(studentId);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long studentId,
                                                    @Valid @RequestBody StudentDto student){
        student.setId(studentId);
        StudentDto updatedStudent = studentService.updateStudent(student);

        return new ResponseEntity<>(updatedStudent,HttpStatus.OK);

    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") Long studentId){
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("The student id is successfully deleted",HttpStatus.OK);
    }
   /* @ExceptionHandler(ResourceNotFoundException.class) // for spetific exception
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }*/


}
