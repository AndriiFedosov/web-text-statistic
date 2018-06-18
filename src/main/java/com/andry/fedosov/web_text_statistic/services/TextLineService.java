package com.andry.fedosov.web_text_statistic.services;

import com.andry.fedosov.web_text_statistic.entity.Line;
import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.repositories.TextLineEntityRepository;
import com.andry.fedosov.web_text_statistic.utils.Pageable;
import entity.TextLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.impl.LineStatisticServiceImpl;

import java.util.List;

@Service
public class TextLineService  {

    @Autowired
    private TextLineEntityRepository repository;


    public Line save(Line entity){
        if(repository.getLastId()!=null){
            entity.setNumberLine(repository.getLastId()+1);
        }else entity.setNumberLine(1);
        return repository.save(entity);
    }

    public Line getById(int lineNumber , int textId){
        return repository.getByNumberLineAndFileId(lineNumber,textId);
    }

    public Integer getLastIdOfLine(){
        return repository.getLastId();
    }

    public Pageable<? extends Line> getLinesOnPage(int id){
        List<Line> entities = repository.getAllLines(id);
        long count = repository.countByFileId(id);
        return new Pageable<>(entities,count);
    }


    public int getLastFileId() {
        return repository.getLastFileId();
    }
}
