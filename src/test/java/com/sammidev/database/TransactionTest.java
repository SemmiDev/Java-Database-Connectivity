package com.sammidev.database;

import com.sammidev.util.ConnectionUtil;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class TransactionTest {

    @Test
    @Order(1)
    void testCommit() throws SQLException {

        var connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO students(name, email, phone) VALUES (?, ?, ?)";
        for (int i = 0; i < 100; i++) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "sammidev");
            preparedStatement.setString(2, "dev@gmail.com");
            preparedStatement.setString(3, "0000000000");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        connection.commit();
        connection.close();
    }


    @Test
    @Order(2)
    void testRollback() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO students(name, email, phone) VALUES (?, ?, ?)";
        for (int i = 0; i < 100; i++) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "sammidev");
            preparedStatement.setString(2, "sammidev@gmail.com");
            preparedStatement.setString(3, "0000000000");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        connection.rollback();
        connection.close();
    }
}
