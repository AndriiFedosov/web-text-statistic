package com.andry.fedosov.web_text_statistic.resorurces;

import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.repositories.TextFileEntityRepository;
import com.andry.fedosov.web_text_statistic.repositories.TextLineEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;

public class TestResourcesRepositories {
    @Autowired
    public TextFileEntityRepository textRepository;
    @Autowired
    public TextLineEntityRepository lineRepository;

    protected void lineSaverWithTextEntity(Text text, List<Line> linesLocal) {
        String line ="I`m now terminator T1000!";
        String longestWord = "terminator";
        String shortestWord = "I`m";
        int averageWord = Math.round(5.5F);

        int lineNumber=1;

        for (int i = 0; i <5 ; i++) {
            Line entity = new Line(lineNumber,line,longestWord,shortestWord,averageWord);
            entity.setFile(text);
            linesLocal.add(entity);
            lineRepository.save(entity);
            lineNumber++;
        }
    }
    protected void textSaver(){
        int id =1;
        for (int i = 0; i <5 ; i++) {
            textRepository.save(new Text(id));
            id++;
        }
    }
    protected void lineSaver(){
        int id =1;
        for (int i = 0; i <5 ; i++) {
            Line entity =new Line();
            entity.setNumberLine(id);
            lineRepository.save(entity);
            id++;
        }
    }
}
