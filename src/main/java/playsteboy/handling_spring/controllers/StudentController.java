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
    public ResponseEntity<String> createStudent(@RequestBody StudentEntity student) {
        List<String> studentsNames = new ArrayList<>();
        for(StudentEntity studentEntity : studentService.setStudent(student)){
            studentsNames.add(studentEntity.getFirstName() + " " + studentEntity.getLastName());
        }

        return ResponseEntity.ok(studentsNames.toString());
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(required = true) String Accept) {
        if(!MediaType.TEXT_PLAIN_VALUE.equals(Accept)){
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Format non supporte");
        }
        List<String> studentsNames = new ArrayList<>();
        for(StudentEntity studentEntity : studentService.getStudents()){
            studentsNames.add(studentEntity.getFirstName() + " " + studentEntity.getLastName());
        }

        return ResponseEntity.ok(studentsNames.toString());
    }
}
