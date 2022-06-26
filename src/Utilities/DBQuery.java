package Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    private static PreparedStatement statement;

    public static void setPreparedStatement(Connection connection, String sqlStatement) throws SQLException {

        statement = connection.prepareStatement(sqlStatement);
    }

    public static PreparedStatement getPreparedStatement() {

        return statement;
    }
}
