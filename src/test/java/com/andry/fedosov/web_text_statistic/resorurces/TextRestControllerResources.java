package com.andry.fedosov.web_text_statistic.resorurces;

import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.services.TextFileService;
import com.andry.fedosov.web_text_statistic.services.TextLineService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

public class TextRestControllerResources {

    @Autowired
    protected WebApplicationContext context;
    @Autowired
    protected TextFileService textService;
    @Autowired
    protected TextLineService lineService;

    protected MockMvc mockMvc;
    protected Text textEntity = new Text(1);
    protected String textLine = "This line not empty";
    protected Line lineEntity = new Line(1,textLine,"This line not empty","not",4);
    protected MediaType mediaType = new MediaType(
                                            MediaType.APPLICATION_JSON.getType(),
                                            MediaType.APPLICATION_JSON.getSubtype(),
                                            Charset.forName("utf-8"));

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        lineEntity.setFile(textEntity);
    }

}
