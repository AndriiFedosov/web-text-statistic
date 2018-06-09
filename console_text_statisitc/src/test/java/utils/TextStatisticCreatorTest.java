package utils;

import dao.impl.TextFileDAOImpl;
import dao.impl.TextLineDAOImpl;
import datasource.DataSourceCreator;
import entity.TextFile;
import entity.TextLine;
import org.junit.Assert;
import org.junit.Test;
import service.impl.LineStatisticServiceImpl;
import service.impl.TextStatisticServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextStatisticCreatorTest {

    private TextStatisticServiceImpl textService = new TextStatisticServiceImpl(new TextFileDAOImpl(DataSourceCreator.getSource()));
    private LineStatisticServiceImpl lineService = new LineStatisticServiceImpl(new TextLineDAOImpl(DataSourceCreator.getSource()));

    private TextStatisticCreator creator = new TextStatisticCreator(textService,lineService);

    @Test
    public void createLineStatistic(){
        String line = "- Доктор, вы знаете, они повсюду! Даже сам Лорд Пневмослон боится этих тварей. СМЕРТЬ! СМЕРТЬ! Я ДОЛЖЕН ВСЕХ УБИТЬ!";
        TextLine testLine = new TextLine(0,line,"Пневмослон","-",5);
        TextLine dbLine = creator.createStatisticInLine(line);
        Assert.assertEquals(testLine,dbLine);
    }

    @Test
    public void createAllStatistic() throws IOException {

        TextFile file = new TextFile();
        TextFile dbFile = creator.createStatisticInFile(TextStatisticCreatorTest.class.getClassLoader().getResource("second/go to Army now.txt").getFile());

        file.setId(dbFile.getId());
        file.setTextLength(10479);
        file.setLongestWord("медсестру-ассистентку.");
        file.setShortestWord("в");
        file.setAverageLengthWord(6);

        Assert.assertEquals(file,dbFile);

    }
    @Test
    public void createStatisticForFileInAllDirectories(){
        TextFile textFile = new TextFile(0,"медсестру-ассистентку.","в",10479,6);
        TextFile textFile1 = new TextFile(0,"медсестру-ассистентку.","в",10479,6);
        TextFile textFile12  = new TextFile(0,"возвратно-поступательные","-",12638,6);
        List<TextFile> files = Arrays.asList(textFile, textFile1, textFile12);
        List<TextFile> statisticDB = creator.getStatisticFromAllFiles(
          TextStatisticCreatorTest.class.getClassLoader()
                  .getResource("secondTest").getFile());
        statisticDB.forEach(text->text.setId(0));

        Assert.assertEquals(files,statisticDB);


    }


}
