package dao;

import dao.impl.TextFileDAOImpl;
import dao.impl.TextLineDAOImpl;
import datasource.DataSourceCreatorTestResource;
import entity.TextFile;
import entity.TextLine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class TextDAOTests {

    private TextFileDAO textDAO = new TextFileDAOImpl(DataSourceCreatorTestResource.getSource());
    private TextLineDAO lineDAO = new TextLineDAOImpl(DataSourceCreatorTestResource.getSource());


    @Test
    public void saveFileStatisticFromDatabase(){
        TextFile file = new TextFile(new Random().nextInt(2500000));
        Assert.assertEquals(file,textDAO.save(file));
    }

    @Test
    public void getFileStatisticFromDatabase(){
        TextFile file = new TextFile(new Random().nextInt(2500000));
        Assert.assertEquals(file,textDAO.getById(textDAO.save(file).getId()));
    }
    @Test
    public void createStatisticFromDataBase(){
        TextFile file =textDAO.save( new TextFile(new Random().nextInt(2500000)));

        TextLine lineOne = new TextLine(1,"I am T800","T800","I",2);
        TextLine lineTwo = new TextLine(2,"I`m back!","back!","I`m",4);

        lineOne.setFile(file);
        lineTwo.setFile(file);

        lineDAO.save(lineOne);
        lineDAO.save(lineTwo);

        file.setTextLength(lineOne.getLine().length()+lineTwo.getLine().length());
        file.setLongestWord(lineTwo.getLongestWord());
        file.setShortestWord(lineOne.getShortestWord());
        file.setAverageLengthWord(Math.round((lineOne.getAverageWordLength()+lineTwo.getAverageWordLength())/2));

        Assert.assertEquals(file,textDAO.createStatFromLines(file.getId()));
    }
}
