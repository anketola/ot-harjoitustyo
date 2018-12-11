package columnspeli.dao;

import columnspeli.domain.ScoreEntry;
import java.sql.*;
import java.util.*;

public class ScoreEntryDao implements Dao <ScoreEntry, Integer> {

    private Database database;

    public ScoreEntryDao(Database database){
        this.database = database;
    }
    
    @Override
    public ArrayList<ScoreEntry> findAll() throws SQLException {
        ArrayList<ScoreEntry> matchingEntries = new ArrayList<>();
        Connection SQLConnection = database.getConnection();
        PreparedStatement prepStatement = SQLConnection.prepareStatement("SELECT * FROM Scores");
        ResultSet results = prepStatement.executeQuery();
        while (results.next()) {
            matchingEntries.add(new ScoreEntry(results.getString("name"), results.getInt("score")));
        }
        prepStatement.close();
        results.close();
        SQLConnection.close();
        
    return matchingEntries;
    }
    
    @Override
    public void save(ScoreEntry scoreEntry) throws SQLException {
        Connection SQLConnection = database.getConnection();
        PreparedStatement prepStatement = SQLConnection.prepareStatement("INSERT INTO Scores"
                + " (name, score)"
                + " VALUES (?, ?)"
        );
        prepStatement.setString(1, scoreEntry.getName());
        prepStatement.setInt(2, scoreEntry.getScore());
        prepStatement.executeUpdate();
        prepStatement.close();
        SQLConnection.close();
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        // TO DO - or is this even needed in time..
    }
    
    
}
