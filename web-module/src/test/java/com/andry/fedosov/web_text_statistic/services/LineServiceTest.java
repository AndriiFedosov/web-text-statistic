package com.andry.fedosov.web_text_statistic.services;

import com.andry.fedosov.web_text_statistic.repositories.TextLineEntityRepository;
import com.andry.fedosov.web_text_statistic.resorurces.TestResourcesServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LineServiceTest extends TestResourcesServices {

    @Mock
    private TextLineEntityRepository repository;

    @InjectMocks
    private TextLineService service;

    @Test
    public void saveLineTest(){
        service.save(line);

        verify(repository,times(1))
                .save(line);
    }

    @Test
    public void getBuIdAndLineNumberTest(){
        service.getById(lineNumber,id);

        verify(repository,times(1))
                .getByNumberLineAndFileId(lineNumber,id);
    }

    @Test
    public void getLastIdTest(){
        service.getLastIdOfLine();

        verify(repository,times(1))
                .getLastId();
    }

    @Test
    public void getLinesOnPageTest(){
        service.getLinesOnPage(id);

        verify(repository,times(1))
                .getAllLines(id);
    }
    @Test
    public void getLastFileIdTest(){
        service.getLastFileId();
        verify(repository,times(1))
                .getLastFileId();
    }

}
