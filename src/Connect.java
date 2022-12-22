import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:\\Program Files\\JetBrains\\IntelliJ IDEA Community Edition 2022.3.1\\jbr\\bin\\sqlite.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
              //  conn.close();
            }
        }
        return conn;
    }
    /**
     * @param args the command line arguments
     */
}
