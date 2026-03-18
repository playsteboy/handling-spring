package playsteboy.handling_spring.services;

import org.springframework.stereotype.Service;
import playsteboy.handling_spring.entities.StudentEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private List<StudentEntity> students = new ArrayList<>();
    public List<StudentEntity> getStudents() {
        return students;
    }
    public List<StudentEntity> setStudent(StudentEntity student) {
        students.add(student);
        return students;
    }
    public List<StudentEntity> getStudents(List<StudentEntity> students) {
        return students;
    }

}
