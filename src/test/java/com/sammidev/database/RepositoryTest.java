package com.sammidev.database;

import com.sammidev.entity.Student;
import com.sammidev.repository.StudentRepository;
import com.sammidev.repository.impl.StudentRepositoryImpl;
import org.junit.jupiter.api.*;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RepositoryTest {

    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository = new StudentRepositoryImpl();
    }


    @Test
    @Disabled("nantik dulu ya")
    void testInsert() {
        var student = Student.builder()
                .name("sammmidev")
                .email("sammmidev@gmail.com")
                .phone("0823873259")
                .build();

        studentRepository.insert(student);
        Assertions.assertNotNull(student.getId());
        System.out.println(student.toString());
    }

    @Test
    void testFindById() {
        var student = studentRepository.findById(1);
        System.out.println(student.toString());
    }

    @Test
    void testFindByEmail() {
        var student = studentRepository.findByEmail("sammmidev@gmail.com");
        System.out.println(student.toString());
    }

    @Test
    void testFindAll() {
        var list = studentRepository.findAll();
        System.out.println(list);
    }
}