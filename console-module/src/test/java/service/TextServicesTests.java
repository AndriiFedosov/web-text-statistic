package service;

import dao.TextFileDAO;
import entity.TextFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.impl.TextStatisticServiceImpl;

import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class TextServicesTests {

    @Mock
    private TextFileDAO dao;
    private TextStatisticService service;

    @Before
    public void createService(){
        service = new TextStatisticServiceImpl(dao);
    }

    @Test
    public void saveServiceMethod(){
        TextFile file = Mockito.mock(TextFile.class);
        service.save(file);
        Mockito.verify(dao,Mockito.times(1)).save(file);
    }

    @Test
    public void getServiceMethod(){
        int id = new Random().nextInt(25000000);
        service.getById(id);
        Mockito.verify(dao,Mockito.times(1)).getById(id);
    }

    @Test
    public void createStatFromLineServiceMethod(){
        TextFile file = new TextFile();
        service.createStatFromLines(file);
        Mockito.verify(dao,Mockito.times(1)).createStatFromLines(file.getId());
    }
}
