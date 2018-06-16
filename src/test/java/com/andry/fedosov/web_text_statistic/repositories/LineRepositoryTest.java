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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = WebTextStatisticApplication.class)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
                         DependencyInjectionTestExecutionListener.class,
                         TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class LineRepositoryTest extends TestResourcesRepositories {

    @Test
    @Transactional
    public void saveLineTest(){
        Line line = new Line();
        Line expected = lineRepository.save(line);

        Assert.assertEquals(line,expected);
    }

    @Test
    @Transactional
    public void getLastIdTest(){
        lineSaver();
        int lastId = lineRepository.getLastId();

        Assert.assertEquals(5,lastId);
    }

    @Test
    @Transactional
    public void getOneLineInFileTest(){
        Text text = new Text(1);
        textRepository.save(text);

        List<Line> linesLocal = new ArrayList<>(5);

        lineSaverWithTextEntity(text, linesLocal);
        Line localLine = linesLocal.get(2);
        localLine.setFile(text);
        Line lineDB = lineRepository.getByNumberLineAndFileId(3,1);

        Assert.assertEquals(localLine,lineDB);
    }

    @Test
    @Transactional
    public void getAllLinesInFile(){
        Text text = new Text(1);
        textRepository.save(text);

        List<Line> linesLocal = new ArrayList<>(5);

        lineSaverWithTextEntity(text, linesLocal);

        List<Line> linesDb = lineRepository.getAllLines(1);

        Assert.assertEquals(linesLocal,linesDb);
    }

    @Test
    @Transactional
    public void getCountOfLineById(){
        Text text = new Text(1);
        textRepository.save(text);

        List<Line> linesLocal = new ArrayList<>(5);

        lineSaverWithTextEntity(text, linesLocal);

        long countFilesByIdInDB = lineRepository.countByFileId(1);

        Assert.assertEquals(linesLocal.size(),countFilesByIdInDB);
    }

}
