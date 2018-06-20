package com.andry.fedosov.web_text_statistic.controllers;

import com.andry.fedosov.web_text_statistic.WebTextStatisticApplication;
import com.andry.fedosov.web_text_statistic.resorurces.TextRestControllerResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = WebTextStatisticApplication.class)
@TestExecutionListeners({DirtiesContextTestExecutionListener.class,
                         DependencyInjectionTestExecutionListener.class,
                         TransactionalTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class TextRestControllerTest extends TextRestControllerResources {

    @Test
    public void getOnFileByIdTest() throws Exception {

        textService.save(textEntity);

        mockMvc.perform(get("/v1/statistics/"+textEntity.getId()+"/text"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.id",is(textEntity.getId())))
                .andExpect(jsonPath("$.longestWord",is(textEntity.getLongestWord())))
                .andExpect(jsonPath("$.shortestWord",is(textEntity.getShortestWord())))
                .andExpect(jsonPath("$.averageLengthWord",is(textEntity.getAverageLengthWord())))
                .andExpect(jsonPath("$.textLength",is((int)textEntity.getTextLength())));
    }

    @Test
    public void getLineFromFileTest() throws Exception {

        textService.save(textEntity);
        lineService.save(lineEntity);

        mockMvc.perform(get("/v1/statistics/" + textEntity.getId() + "/lines"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.components[0].numberLine",is(lineEntity.getNumberLine())))
                .andExpect(jsonPath("$.components[0].file.id",is(lineEntity.getFile().getId())))
                .andExpect(jsonPath("$.components[0].line",is(lineEntity.getLine())))
                .andExpect(jsonPath("$.components[0].longestWord",is(lineEntity.getLongestWord())))
                .andExpect(jsonPath("$.components[0].shortestWord",is(lineEntity.getShortestWord())))
                .andExpect(jsonPath("$.components[0].averageWordLength",is(lineEntity.getAverageWordLength())))
                .andExpect(jsonPath("$.elementsCount",is(1)));
    }

    @Test
    public void getAllFilesTest() throws Exception {
        textService.save(textEntity);
        textService.save(textEntity);
        textService.save(textEntity);

        mockMvc.perform(get("/v1/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.components").isArray())
                .andExpect(jsonPath("$.components").isNotEmpty())
                .andExpect(jsonPath("$.elementsCount").isNumber())
                .andExpect(jsonPath("$.elementsCount",is(3)));
    }

    @Test
    public void postFileTest() throws Exception {
        mockMvc.perform(post("/v1/statistics")
                        .contentType(mediaType)
                        .content(new ObjectMapper().createObjectNode()
                                .put("text","I hurt myself today").toString()))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id",notNullValue()))
                        .andExpect(jsonPath("$.longestWord",notNullValue()))
                        .andExpect(jsonPath("$.shortestWord",notNullValue()))
                        .andExpect(jsonPath("$.averageWordLength",notNullValue()))
                        .andExpect(jsonPath("$.textLength",notNullValue()));
    }

}

