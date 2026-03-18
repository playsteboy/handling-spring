package playsteboy.handling_spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playsteboy.handling_spring.entities.StudentEntity;
import playsteboy.handling_spring.services.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody StudentEntity student) {
        try{
            return ResponseEntity.status(201).body(studentService.setStudent(student));
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(required = true) String Accept) {
        try{
            if(Accept == null || Accept.isEmpty()){
                return ResponseEntity.status(400).body("Accept header is null");
            }
            if(!MediaType.TEXT_PLAIN_VALUE.equals(Accept) &&  !MediaType.APPLICATION_JSON_VALUE.equals(Accept)){
                return ResponseEntity.status(501).body("Format non supporte");
            }
            if (MediaType.TEXT_PLAIN_VALUE.equals(Accept)) {
                String studentsString = studentService.getStudents().toString();
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(studentsString);
            }
            return ResponseEntity.ok(studentService.getStudents());
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
