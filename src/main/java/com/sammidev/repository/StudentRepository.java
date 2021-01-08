package com.sammidev.repository;

import com.sammidev.entity.Student;

import java.util.List;

public interface StudentRepository {

    void insert(Student student);
    Student findById(Integer id);
    List<Student> findByName(String email);
    List<Student> findByEmail(String email);
    List<Student> findByPhone(String phone);
    List<Student> findAll();
}