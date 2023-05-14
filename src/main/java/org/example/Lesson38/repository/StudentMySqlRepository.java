package org.example.Lesson38.repository;

import org.example.Lesson38.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMySqlRepository implements StudentRepository {

    static final String DB_URL = "jdbc:mysql://localhost/robot_dreams";
    static final String USER = "root";
    static final String PASS = "1234";
    static final String SELECT_FROM_STUDENTS = "SELECT * FROM Students";
    static final String FIND_STUDENT_BY_ID = "SELECT * FROM Students WHERE id = ?";
    static final String INSERT_STUDENT = "INSERT INTO students (name, Student_age, Student_group_id) VALUES (?, ?, ?)";

//    static final String ADD

    @Override
    public void addStudentInDataBase(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setInt(3, student.getGroupId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override

    public List<Student> readListOfStudents() {
        List<Student> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_FROM_STUDENTS)) {
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("Student_age"))
                        .groupId(rs.getInt("Student_group_id"))
                        .build();
                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Student findStudentById(int id) {
        Student student = new Student();
        PreparedStatement ps;
        ResultSet rs;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            //  Statement stmt = conn.createStatement();
            //  ResultSet rs = stmt.executeQuery(FIND_STUDENT_BY_ID + id)) {
            ps = conn.prepareStatement(FIND_STUDENT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            student = Student.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("Student_age"))
                    .groupId(rs.getInt("Student_group_id"))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

}
