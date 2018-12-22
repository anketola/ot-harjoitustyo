package columnspeli.dao;

import java.sql.*;
import java.util.*;

/**
 * DAO tietokantayhteyksiä varten.
 * 
 * @param <T> tässä sovelluksen toteuksessa ScoreEntry-olio.
 */

public interface Dao<T> {
    
    List<T> findAll() throws SQLException;
    
    void save(T object) throws SQLException;
    
}
