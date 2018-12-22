package columnspeli.dao;

import java.sql.*;

/**
 * Tietokantayhteyden muodostamiseen käytettävä luokka.
 * 
 */

public class Database {
   
    private String databaseString;
    
    public Database(String databaseString) throws ClassNotFoundException {
        this.databaseString = databaseString;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseString);
    }
    
}
