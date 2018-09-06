package General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

    private static final String USERNAME = "usuario";
    private static final String PASSWORD = "usuario";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/hr?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    public static Connection createConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    CONNECTION_STRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connector > createConnection > SQLException:");
            System.err.println(e);
        }

        return connection;
    }

    public static boolean updateDatabase(String queryString) {
        boolean result = true;
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();

            statement.executeUpdate(queryString);
            statement.close();

            connection.close();
        } catch (SQLException e) {
            System.err.println("Connector > updateDatabase > SQLExeption.");
            System.err.println(e);
            result = false;
        }

        return result;
    }

}
