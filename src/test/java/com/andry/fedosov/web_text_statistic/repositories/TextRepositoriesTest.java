package com.andry.fedosov.web_text_statistic.repositories;

import com.andry.fedosov.web_text_statistic.WebTextStatisticApplication;
import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.resorurces.TestResourcesRepositories;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@SpringBootTest(classes = WebTextStatisticApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
                         DependencyInjectionTestExecutionListener.class,
                         TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TextRepositoriesTest extends TestResourcesRepositories {

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getListOfTextFilesTest() {
        List <Text> textsLocal = new LinkedList<>();
        for (int i = 1; i <=5 ; i++) {
            textsLocal.add(new Text(i));
        }
        textSaver();

        List<Text> textsDB = textRepository.findAll();

        Assert.assertEquals(textsLocal,textsDB);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void saveTextFileStatisticTest(){
        Text text = new Text(1);
        Text savedStatistic = textRepository.save(text);

        Assert.assertEquals(text,savedStatistic);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getTextFileByIdTest(){
        Text text =  new Text(1);
        Text gettingText = textRepository.getById(textRepository.save(text).getId());

        Assert.assertEquals(text,gettingText);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getTextWithFilteredLinesTest(){
        Text text = textRepository.save(new Text(1));
        Text text2 = textRepository.save(new Text(2));
        List<Line> linesLocal = new ArrayList<>(5);

        lineSaverWithTextEntity(text, linesLocal);
        lineSaverWithTextEntity(text2, linesLocal);


        List<? extends Text> texts = textRepository.textHaveMoreLines(2);

        Assert.assertEquals(2,texts.size());
    }


    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getCountOfFilteredTextsTest(){
        Text text = textRepository.save(new Text(9));

        List<Line> linesLocal = new ArrayList<>(5);


        lineSaverWithTextEntity(text, linesLocal);

        long countFilteredTexts = textRepository.countOfFilteredTexts(3);

        Assert.assertEquals(1,countFilteredTexts);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getCountOfTextsTest(){
        textSaver();

        long countOfTexts = textRepository.count();

        Assert.assertEquals(5L,countOfTexts);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void getLastIdTest(){
        textSaver();
        int lastId = textRepository.getLastId();
        Assert.assertEquals(5,lastId);
    }

    @Test
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateStatisticInFile(){

        Text text = new Text(1);
        textRepository.save(text);

        List<Line> linesLocal = new ArrayList<>(5);

        lineSaverWithTextEntity(text, linesLocal);

        textRepository.updateTextStatisticFromLines(1);

        Text currentText = textRepository.getById(1);

        Text finalText = new Text(1,"terminator","I`m",125L,6);

        Assert.assertEquals(currentText,finalText);

    }


}
