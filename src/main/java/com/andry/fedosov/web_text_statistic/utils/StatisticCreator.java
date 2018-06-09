package com.andry.fedosov.web_text_statistic.utils;


import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.services.TextFileService;
import com.andry.fedosov.web_text_statistic.services.TextLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class StatisticCreator {

    private TextFileService fileService;
    private TextLineService lineService;

    @Autowired
    public StatisticCreator(TextFileService fileService, TextLineService  lineService){
        this.fileService = fileService;
        this.lineService = lineService;
    }

    public Text createStatisticInFile(String textFile){

        Text text = new Text();
        List<String> textLines = Arrays.asList(textFile.split("\n"));
        Integer lineNumber = 1;
        if(lineService.getLastIdOfLine()!= null){
            lineNumber = lineService.getLastIdOfLine()+1;
        }
        text = fileService.save(text);
        Text finalText = text;
        for (String line : textLines) {
            Line lineEntity = createStatisticInLine(line);
            lineEntity.setNumberLine(lineNumber);
            lineEntity.setFile(finalText);
            lineService.save(lineEntity);
            lineNumber++;
        }
        return fileService.createStatFromLines(text);
    }

    public Line createStatisticInLine(String line) {
        String temp = line.replaceAll(" ","");

        if(temp.isEmpty()) {
            return new Line(0, line, null, null, 0);
        }

        List<String> words = Stream.of(line.split(" ")).filter(s -> !s.isEmpty()).collect(Collectors.toList());

        Line entity = new Line();
        entity.setLine(line);
        entity.setLongestWord(words.stream().max(Comparator.comparingInt(String::length)).orElse(null));
        entity.setShortestWord(words.stream().min(Comparator.comparingInt(String::length)).orElse(null));
        entity.setAverageWordLength((int)Math.round(words.stream().mapToInt(String::length).average().orElse(0)));

        return entity;

    }
}