package com.sammidev.database;

import org.junit.jupiter.api.*;

import java.sql.DriverManager;
import java.sql.SQLException;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try {
            var mysqldriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqldriver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void testOpenConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc_clean_arch?serverTimezone=Asia/Jakarta";
        String username = "root";
        String password = "";

        try {
            var connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("CONNECTED");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    @Order(2)
    void testCloseConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc_clean_arch?serverTimezone=Asia/Jakarta";
        String username = "root";
        String password = "";

        try {
            var connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("DISCONNECTED");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}