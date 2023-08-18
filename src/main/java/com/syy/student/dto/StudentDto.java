package com.syy.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    private Long id;


    @Schema(
            description = "Student Name"
    )
    @NotNull(message = "student name should not be empty or null")
    private String name;

    @NotNull
    private int age;
    @NotEmpty
    private String grade;

    @NotEmpty
    @Email(message = "Email address should be valid")
    private String email;
}
