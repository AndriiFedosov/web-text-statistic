package com.andry.fedosov.web_text_statistic.services;

import com.andry.fedosov.web_text_statistic.entity.Text;
import com.andry.fedosov.web_text_statistic.repositories.TextFileEntityRepository;
import com.andry.fedosov.web_text_statistic.utils.Pageable;
import entity.TextFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TextFileService  {

    @Autowired
    private TextFileEntityRepository repository;

    public Text save(Text entity){
        if(repository.getLastId()!=null){
            entity.setId(repository.getLastId()+1);
        }else entity.setId(1);
        return repository.save(entity);
    }

    public Text getById(int id){
        return repository.getById(id);
    }

    public Text createStatFromLines(TextFile textFile) {
        repository.updateTextStatisticFromLines(textFile.getId());
        return repository.getById(textFile.getId());
    }

    public Pageable<? extends Text> getTextsFilterOnPage(int lines){
        if (lines == 1){
            return getTexts();
        }
        return getTextWithFilter(lines);
    }

    public Pageable<? extends Text> getTexts(){
        List<Text> entities = repository.findAll();
        long count = repository.count();
        return  new Pageable<>(entities,count);
    }

    public Pageable<? extends Text> getTextWithFilter(int lines){
        List<Text> entities = repository.textHaveMoreLines(lines);
        long count = repository.countOfFilteredTexts(lines);
        return new Pageable<>(entities,count);
    }

}
