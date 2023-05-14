package org.example.Lesson38.repository;


import org.example.Lesson38.domain.Student;

import java.util.List;

public interface StudentRepository {

    void addStudentInDataBase(Student student);

    List<Student> readListOfStudents();

    Student findStudentById(int id);

}
