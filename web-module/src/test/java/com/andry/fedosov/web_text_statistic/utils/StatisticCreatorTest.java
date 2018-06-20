package com.andry.fedosov.web_text_statistic.utils;

import com.andry.fedosov.web_text_statistic.WebTextStatisticApplication;
import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;
import entity.TextFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@SpringBootTest(classes = WebTextStatisticApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class StatisticCreatorTest {

    @Autowired
    private StatisticCreator creator;

    @Test
    public void createStatisticTest(){
        Text file = new Text();
        String text = "I hurt myself today\n" +
                "To see if I still feel\n" +
                "I focus on the pain\n" +
                "The only thing that's real";
        Text dbFile = creator.createStatisticInFile(text);

        file.setId(dbFile.getId());
        file.setTextLength(86);
        file.setLongestWord("myself");
        file.setShortestWord("I");
        file.setAverageLengthWord(4);

        Assert.assertEquals(file,dbFile);
    }

    @Test
    public void createStatisticInLineTest(){
        String textLine ="I hurt myself today" ;
        Line line = new Line(0,textLine,"myself","I",4);
        Line lineDb = creator.createStatisticInLine(textLine);
        Assert.assertEquals(line,lineDb);
    }
}
