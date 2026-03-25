package playsteboy.handling_spring.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playsteboy.handling_spring.entities.StudentEntity;
import playsteboy.handling_spring.services.StudentService;
import playsteboy.handling_spring.validator.StudentValidator;


import java.util.List;

@RestController
public class StudentController {
    private StudentService studentService;
    private StudentValidator studentValidator ;
    public StudentController(StudentService studentService,  StudentValidator studentValidator) {
        this.studentService = studentService;
        this.studentValidator = studentValidator;
    }

    @PostMapping("/students")
    public ResponseEntity<?> createStudent(@RequestBody List<StudentEntity> newStudents) {
        try{
            List<StudentEntity> validatedStudents = studentValidator.validateStudents(newStudents);
            if(validatedStudents != null){
                return ResponseEntity.status(201).body(studentService.setStudent(validatedStudents));
            }
            return ResponseEntity.status(400).body("no new students found");
        }catch(Exception e){
            if(e.getCause()!=null){
                if(e.getCause().getClass().equals(BadRequestException.class)) {
                    return ResponseEntity.status(400).body(e.getMessage());
                }
            }
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(required = false) String accept) {
        try{
            if(accept == null || accept.isBlank()){
                return ResponseEntity.status(400).body("accept header can not be null or blank");
            }
            if(!accept.contains(MediaType.TEXT_PLAIN_VALUE) &&  !accept.contains(MediaType.APPLICATION_JSON_VALUE)){
                return ResponseEntity.status(501).body("Format non supporte");
            }
            if (accept.contains(MediaType.TEXT_PLAIN_VALUE)) {
                String studentsString = studentService.getStudents().toString();
                return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(studentsString);
            }
            return ResponseEntity.ok(studentService.getStudents());
        }catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}
