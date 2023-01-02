package com.apidemo.controllers;

import com.apidemo.Service.ICourseService;
import com.apidemo.dto.CourseDTO;
import com.apidemo.models.Course;
import com.apidemo.models.ResponseObject;
import com.apidemo.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;

    @PostMapping("/create_course")
    public ResponseEntity<ResponseObject> createCourse(@RequestBody CourseDTO courseDTO) throws Exception {
        CourseDTO courseDTO1 = courseService.create(courseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert student successfully", courseDTO1)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateCourse(@RequestBody CourseDTO courseDTO) throws Exception {
        CourseDTO courseDTO1 = courseService.update(courseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert student successfully", courseDTO1)
        );
    }

    @GetMapping("")
    List<Course> getAllCourse() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) throws Exception {
        CourseDTO courseDTO = courseService.getDetail(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert student successfully", courseDTO)
        );
    }
}
