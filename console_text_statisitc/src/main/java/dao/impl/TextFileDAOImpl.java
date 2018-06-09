package dao.impl;

import dao.TextFileDAO;
import entity.TextFile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TextFileDAOImpl implements TextFileDAO {

    private final DataSource dataSource;

    public TextFileDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public TextFile save(TextFile textFile) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO text_stat VALUES(?,?,?,?,?)")){

            int id = textFile.getId();
            if(id == 0){
                id = new Random().nextInt(25000000);
            }

            statement.setInt(1,id);
            statement.setString(2, textFile.getLongestWord());
            statement.setString(3, textFile.getShortestWord());
            statement.setLong(4, textFile.getTextLength());
            statement.setInt(5, textFile.getAverageLengthWord());

            if (statement.executeUpdate() == 0) {
                throw new IllegalStateException("We cant add file statistic in text_stat");
            }

        }catch (SQLException e){
            throw new IllegalStateException("something went wrong, cant connect to datasource.",e);
        }
        return getById(textFile.getId());
    }

    @Override
    public TextFile getById(int id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM text_stat  WHERE id = ?")) {

            statement.setInt(1,id);
            statement.execute();
            try (ResultSet resultSet = statement.getResultSet()) {
                if (resultSet.next()) {
                    return new TextFile(
                            resultSet.getInt("id"),
                            resultSet.getString("longest_word"),
                            resultSet.getString("shortest_word"),
                            resultSet.getInt("text_length"),
                            resultSet.getInt("average_word_length"));
                }
            }

        }catch (SQLException e){
            throw new IllegalStateException("We cant get this file from data base.",e);
        }
        return null;
    }


    @Override
    public TextFile createStatFromLines(int id) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE text_stat SET longest_word = (SELECT l.longest_word FROM line AS l WHERE l.file_id = ? AND l.longest_word IS NOT NULL ORDER BY CHAR_LENGTH(l.longest_word) DESC LIMIT 1), " +
                            "shortest_word = (SELECT l.shortest_word FROM line AS l WHERE l.file_id = ? AND l.shortest_word IS NOT NULL ORDER BY CHAR_LENGTH(l.shortest_word) LIMIT 1), " +
                            "text_length = (SELECT SUM(CHAR_LENGTH(l.line)) FROM line AS l WHERE l.file_id = ?), " +
                            "average_word_length = (SELECT AVG(l.average_word_length) FROM line AS l WHERE l.file_id = ? AND l.average_word_length != 0) WHERE id = ?")) {
            for (int i = 1; i <=5 ; i++) {
                statement.setInt(i,id);
            }
            if (statement.executeUpdate() == 0 ){
                throw new IllegalStateException();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return getById(id);
    }
}
