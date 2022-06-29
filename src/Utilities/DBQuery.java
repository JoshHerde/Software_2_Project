package Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBQuery {

    private static PreparedStatement ps;

    public static void setPreparedStatement(Connection connection, String sql) throws SQLException {

        ps = connection.prepareStatement(sql);
    }

    public static PreparedStatement getPreparedStatement() {

        return ps;
    }
}
