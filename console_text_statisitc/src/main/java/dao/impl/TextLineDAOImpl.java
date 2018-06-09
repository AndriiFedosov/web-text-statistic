package dao.impl;

import dao.TextFileDAO;
import dao.TextLineDAO;
import entity.TextLine;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextLineDAOImpl implements TextLineDAO {

    private DataSource dataSource;


    public TextLineDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public TextLine save(TextLine textLine) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO line VALUES(?,?,?,?,?,?)")){

            statement.setInt(1,textLine.getNumberLine());
            statement.setInt(2,textLine.getFile().getId());
            statement.setString(3,textLine.getLine());
            statement.setString(4,textLine.getLongestWord());
            statement.setString(5,textLine.getShortestWord());
            statement.setInt(6,textLine.getAverageWordLength());

            if(statement.executeUpdate()==0){
                throw new IllegalStateException("Can`t create line for query");
            }

        }catch (Exception e){
            throw new IllegalStateException("Something went wrong, cant connect to datasource.",e);
        }
        return getById(textLine.getFile().getId(),textLine.getNumberLine());
    }

    @Override
    public TextLine getById(int textId, int numOfLine) {
        TextLine line = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM line WHERE file_id = ? AND line_number = ? ")){
                statement.setInt(1,textId);
                statement.setInt(2,numOfLine);
                statement.execute();
                try(ResultSet resultSet = statement.getResultSet()){
                    if(resultSet.next()){
                        line = new TextLine(
                          resultSet.getInt("line_number"),
                          resultSet.getString("line"),
                          resultSet.getString("longest_word"),
                          resultSet.getString("shortest_word"),
                          resultSet.getInt("average_word_length"));
//                        line.setFile(textFileDAO.getById(resultSet.getInt("text_id")));
                    }
                }
        } catch (SQLException e) {

            throw new IllegalStateException("We cant get Line with file_id=" + textId + " and number of line=" + line, e);
        }

        return line;
    }
}
