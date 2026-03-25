package playsteboy.handling_spring.validator;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;
import playsteboy.handling_spring.entities.StudentEntity;
import playsteboy.handling_spring.services.StudentService;

import java.util.List;
@Component
public class StudentValidator {
    StudentService studentService;
    StudentValidator() {
    }
    public List<StudentEntity> validateStudents(List<StudentEntity> students) {
        try {
            for (StudentEntity student : students) {
                if (student.getReference() == null || student.getReference().isBlank()) {
                    throw new BadRequestException("student reference can not be blank or null");
                }
                if (student.getFirstName() == null || student.getFirstName().isBlank()) {
                    throw new BadRequestException(student.getReference() +" firstName can not be blank or null");
                }
                if (student.getLastName() == null || student.getLastName().isBlank()) {
                    throw new BadRequestException(student.getReference() + " lastName can not be blank or null");
                }

            }
            return students;
        }catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

}
