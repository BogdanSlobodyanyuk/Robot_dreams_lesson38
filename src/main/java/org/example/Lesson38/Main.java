package org.example.Lesson38;

import org.example.Lesson38.domain.Student;
import org.example.Lesson38.repository.StudentMySqlRepository;
import org.example.Lesson38.repository.StudentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentRepository sr = new StudentMySqlRepository();
        List<Student> students = sr.readListOfStudents();
        Student student = sr.findStudentById(1);

        Student student1 = Student.builder()
                .age(17)
                .groupId(8)
                .name("Bohdan")
                .build();

        sr.addStudentInDataBase(student1);

        System.out.println(students);
        System.out.println(student);

    }
}