package edu.miu.sa.batchprocessor.mapper;

import edu.miu.sa.batchprocessor.entity.Student;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class StudentFieldMapper implements FieldSetMapper<Student> {

    @Override
    public Student mapFieldSet(FieldSet fieldSet) {
        Student student = new Student();
        student.setFirstName(fieldSet.readString("first_name"));
        student.setLastName(fieldSet.readString("last_name"));
        student.setGpa(fieldSet.readDouble("gpa"));
        student.setAge(fieldSet.readInt("age"));
        return student;
    }
}

