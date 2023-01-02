package com.apidemo.dto;

import com.apidemo.models.Student;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CourseDTO {
    Long id;
    String name;
    Long quantity;
    List<Student> studentList;


}
