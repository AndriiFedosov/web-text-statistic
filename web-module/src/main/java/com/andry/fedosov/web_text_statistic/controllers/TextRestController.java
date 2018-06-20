package com.andry.fedosov.web_text_statistic.controllers;

import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.services.TextFileService;
import com.andry.fedosov.web_text_statistic.services.TextLineService;
import com.andry.fedosov.web_text_statistic.utils.Pageable;
import com.andry.fedosov.web_text_statistic.utils.StatisticCreator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
@RequestMapping(value = "/v1/statistics")

public class TextRestController {

    @Resource(name = "textFileService")
    private TextFileService fileService;
    @Resource(name = "textLineService")
    private TextLineService lineService;
    @Resource(name = "statisticCreator")
    private StatisticCreator creator;

    @Autowired
    public TextRestController(TextFileService fileService, TextLineService lineService, StatisticCreator creator) {
        this.fileService = fileService;
        this.lineService = lineService;
        this.creator = creator;
    }

    @GetMapping(value = "/{id}/text", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getText(@PathVariable("id") int id){
        if(id > fileService.getLastId()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(fileService.getById(id));
    }



    @GetMapping(value = "/{id}/lines", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllLinesInFile(@PathVariable("id") int id){
        if(id > lineService.getLastFileId()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(lineService.getLinesOnPage(id));
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllTexts(@RequestParam(value = "min", required = false, defaultValue = "1") int min){
        if(min < 1){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Pageable<? extends Text> texts = fileService.getTextsFilterOnPage(min);
        if (texts.getComponents().isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(texts);
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> uploadFileAndCreateStatistic(@RequestBody String file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(file);
        String text = json.get("text").textValue();
        Text entity = creator.createStatisticInFile(text);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}/text")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(mapper.createObjectNode()
                .put("id",entity.getId())
                .put("longestWord",entity.getLongestWord())
                .put("shortestWord",entity.getShortestWord())
                .put("averageWordLength",entity.getAverageLengthWord())
                .put("textLength",entity.getTextLength())
        );
    }
}
