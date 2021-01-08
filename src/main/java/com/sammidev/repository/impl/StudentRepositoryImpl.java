package com.sammidev.repository.impl;

import com.sammidev.entity.Student;
import com.sammidev.repository.StudentRepository;
import com.sammidev.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    @Override
    public void insert(Student student) {
        try(Connection connection = ConnectionUtil.getDataSource().getConnection()) {

            String sql = "INSERT INTO students(name,email,phone) VALUES (?, ?,?)";

            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getEmail());
                statement.setString(3, student.getPhone());
                statement.executeUpdate();

                try(ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        student.setId(resultSet.getInt(1));
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Student findById(Integer id) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {

            String sql = "SELECT * FROM students WHERE id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("phone")
                        );
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Student> findByName(String email) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM students WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        students.add(new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return students;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Student> findByEmail(String email) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM students WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        students.add(new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("phone")
                        ));
                    }
                    return students;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public List<Student> findByPhone(String phone) {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {
            String sql = "SELECT * FROM students WHERE phone = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, phone);
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        students.add(new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("comment")
                        ));
                    }
                    return students;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public List<Student> findAll() {
        try (Connection connection = ConnectionUtil.getDataSource().getConnection()) {

            String sql = "SELECT * FROM students";

            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    List<Student> students = new ArrayList<>();
                    while (resultSet.next()) {
                        students.add(new Student(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getString("phone")
                        ));
                    }
                    return students;
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
