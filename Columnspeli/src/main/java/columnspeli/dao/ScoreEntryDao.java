package columnspeli.dao;

import java.sql.*;
import java.util.*;

public class ScoreEntryDao implements Dao <ScoreEntry, Integer> {

    private Database database;

    public ScoreEntryDao(Database database){
        this.database = database;
    }
    
    @Override
    public ArrayList<ScoreEntry> findAllMatching() throws SQLException {
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
        if (matchingEntries.size() < 10) {
            while (matchingEntries.size() < 10)
                matchingEntries.add(new ScoreEntry("Empty", 0));
        }
    return matchingEntries;
    }
    
    @Override
    public ScoreEntry save(ScoreEntry scoreEntry) {
        return null;
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        // TO DO
    }
    
    
}
