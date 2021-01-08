package com.sammidev.database;

import com.sammidev.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class StatementTest {

    @Test
    void testCreateStatement() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();
        String sql = "INSERT INTO employee(id, name, nickname) VALUES ('sam', 'sammidev', 'sam')";
        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteDelete() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        String sql = "DELETE FROM employee";
        int update = statement.executeUpdate(sql);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQuery() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        String sql = " SELECT * FROM employee";
        var resultSet = statement.executeQuery(sql);

        resultSet.close();
        statement.close();
        connection.close();
    }
}