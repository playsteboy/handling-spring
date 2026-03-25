package playsteboy.handling_spring.services;

import org.springframework.stereotype.Service;
import playsteboy.handling_spring.entities.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<StudentEntity> students = new ArrayList<>();
    public StudentService() {}
    public List<StudentEntity> getStudents() {
        return students;
    }
    public List<StudentEntity> setStudent(List<StudentEntity> newStudents) {
        students.addAll(newStudents);
        return students;
    }
    public List<StudentEntity> getStudents(List<StudentEntity> students) {
        return students;
    }

}
