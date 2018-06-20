package com.andry.fedosov.web_text_statistic.resorurces;

import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;

import org.mockito.Mockito;

public class TestResourcesServices {

    protected int id = 1;
    protected int limit = 5;
    protected int offset = 0;
    protected int minimumLines =3;
    protected int lineNumber = 3;
    protected Text text = Mockito.mock(Text.class);
    protected Line line = Mockito.mock(Line.class);
}
