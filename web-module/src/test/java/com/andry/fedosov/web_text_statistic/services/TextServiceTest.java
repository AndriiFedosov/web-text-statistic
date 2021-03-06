package com.andry.fedosov.web_text_statistic.services;

import com.andry.fedosov.web_text_statistic.repositories.TextFileEntityRepository;
import com.andry.fedosov.web_text_statistic.resorurces.TestResourcesServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class TextServiceTest extends TestResourcesServices {

    @Mock
    private TextFileEntityRepository repository;

    @InjectMocks
    private TextFileService service;

    @Test
    public void saveTextFileTest(){

        service.save(text);

        verify(repository,times(1))
                .save(text);
    }

    @Test
    public void getByIdTest(){

        service.getById(id);

        verify(repository,times(1))
                .getById(id);
    }
    @Test
    public void createStatisticFileTest(){
        service.createStatFromLines(text);

        verify(repository,times(1))
                .updateTextStatisticFromLines(text.getId());
    }

    @Test
    public void getFilesWitLimitAndOffset(){
        service.getTexts();

        verify(repository,times(1))
                .findAll();
    }

    @Test
    public void getTextWithFilteredLinesTest(){

        service.getTextWithFilter(minimumLines);

        verify(repository,times(1))
                .textHaveMoreLines(minimumLines);
    }

    @Test
    public void getFilteredTextsOnPage(){
        service.getTextsFilterOnPage(minimumLines);

        verify(repository,times(1))
                .textHaveMoreLines(minimumLines);
    }

    @Test
    public void getLastId(){
        service.getLastId();
        verify(repository,times(1))
                .getLastId();
    }
}
