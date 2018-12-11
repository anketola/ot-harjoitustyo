package columnspeli.dao;

import java.sql.*;
import java.util.*;


public interface Dao<T, K> {
    
    List<T> findAll() throws SQLException;
    
    void save (T object) throws SQLException;
    
    void delete(K key) throws SQLException;
    
}
