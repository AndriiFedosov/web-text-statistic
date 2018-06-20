package dao;

import dao.impl.TextFileDAOImpl;
import dao.impl.TextLineDAOImpl;
import datasource.DataSourceCreatorTestResource;
import entity.TextFile;
import entity.TextLine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class LineDAOTests {

    private TextFileDAO textDAO = new TextFileDAOImpl(DataSourceCreatorTestResource.getSource());
    private TextLineDAO lineDAO = new TextLineDAOImpl(DataSourceCreatorTestResource.getSource());

    private  TextLine line;
    private  TextFile file = new TextFile(new Random().nextInt(25000000));
    private  int numberOfLine = 1;
    private  String textLine = "- Доктор, вы знаете, они повсюду! Даже сам Лорд Пневмослон боится этих тварей. СМЕРТЬ! СМЕРТЬ! Я ДОЛЖЕН ВСЕХ УБИТЬ!";



    @Before
    public  void createStatisticBeforeInitialization(){
        file = textDAO.save(file);
        line = new TextLine(numberOfLine,textLine,"Пневмослон","-",5);
        line.setFile(file);
    }

    @Test
    public void saveLineFromDatabase(){
        TextLine savedLine = lineDAO.save(line);
        savedLine.setFile(file);
        Assert.assertEquals(line,savedLine);
    }

    @Test
    public void getLineFromDatabase(){
        TextLine savedLine = lineDAO.save(line);
        savedLine.setFile(file);

        TextLine gettingLine = lineDAO.getById(savedLine.getFile().getId(),numberOfLine);
        gettingLine.setFile(file);

        Assert.assertEquals(line,gettingLine);
    }

}
