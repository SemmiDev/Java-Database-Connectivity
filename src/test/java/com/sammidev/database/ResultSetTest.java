package com.sammidev.database;

import com.sammidev.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ResultSetTest {

    @Test
    void testExecuteQuery() throws SQLException {
        var connection = ConnectionUtil.getDataSource().getConnection();
        var statement = connection.createStatement();

        String sql = "SELECT * FROM students";
        var resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            var id = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var email = resultSet.getString("email");
            var phone = resultSet.getString("phone");

            System.out.println(String.join(", ", String.valueOf(id), name, email, phone));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
