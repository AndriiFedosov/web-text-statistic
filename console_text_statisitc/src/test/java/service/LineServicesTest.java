package service;

import dao.impl.TextLineDAOImpl;
import entity.TextLine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.impl.LineStatisticServiceImpl;

import java.util.Random;


@RunWith(MockitoJUnitRunner.class)
public class LineServicesTest {
    @Mock
    private TextLineDAOImpl dao;
    private LineStatisticService service;

    @Before
    public void createService(){
        service = new LineStatisticServiceImpl(dao);
    }

    @Test
    public void saveServiceMethod(){
        TextLine line = Mockito.mock(TextLine.class);
        service.save(line);
        Mockito.verify(dao, Mockito.times(1)).save(line);
    }
    @Test
    public void getServiceMethod(){
        int id = new Random().nextInt(2500000);
        int lineNumber = 1;
        service.getById(id,lineNumber);
        Mockito.verify(dao, Mockito.times(1)).getById(id,lineNumber);
    }

}
