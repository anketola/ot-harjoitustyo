package columnspeli.dao;

import java.sql.*;

public class Database {
   
    private String databaseString;
    
    public Database(String databaseString) throws ClassNotFoundException {
        this.databaseString = databaseString;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseString);
    }
    
}
