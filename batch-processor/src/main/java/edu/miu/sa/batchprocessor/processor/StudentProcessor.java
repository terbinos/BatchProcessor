package edu.miu.sa.batchprocessor.processor;

import edu.miu.sa.batchprocessor.entity.Student;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public Student process(Student student) {
        Student processedStudent = new Student();
        processedStudent.setFirstName(student.getFirstName());
        processedStudent.setLastName(student.getLastName());
        processedStudent.setGpa(student.getGpa());
        LocalDateTime dob = LocalDateTime.of(LocalDate.now().getYear() - student.getAge(),1,1,0,0);
        processedStudent.setDob(dob);
        return processedStudent;
    }
}