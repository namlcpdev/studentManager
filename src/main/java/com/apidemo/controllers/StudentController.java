package com.apidemo.controllers;

import com.apidemo.models.ResponseObject;
import com.apidemo.models.Student;
import com.apidemo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Students")
public class StudentController {
    //DI = Dependency Injection
    @Autowired
    private StudentRepository repositoryStudent;

    @GetMapping("")
    List<Student> getAllStudents() {
        return repositoryStudent.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Student> student = repositoryStudent.findById(id);
        return student.isPresent() ?
             ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query student successfully", student)
            ):
            ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Can't find student with id = " + id, null)
            );
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertStudent(@RequestBody Student student) {
        boolean studentByCode = repositoryStudent.existsByCode(student.getCode());
        System.out.println("studentByCode" + studentByCode);
        if(!studentByCode) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Insert student successfully", repositoryStudent.save(student))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("error", "Student code exists", null)
            );
        }
    }
}
