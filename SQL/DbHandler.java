package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbHandler {

    protected Connection connection;

    public Connection getConnection() {

        final String connectionString="jdbc:mysql://localhost:3306/varosi_tomegkozlekedes";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection= DriverManager.getConnection(connectionString, "test2", "test123");
        }catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
