package com.sammidev.database;

import com.sammidev.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class BatchTest {

    @Test
    void testStatement() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        String sql = "INSERT INTO students(name, email, phone) VALUES ('sam','sammidev@gmail.com', '0000000000')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();
        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO students(name, email, phone) VALUES (?, ?, ?)";
        var preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "sammidev");
            preparedStatement.setString(2, "sam@gmail.com");
            preparedStatement.setString(3, "0000000222");
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}
