package com.andry.fedosov.web_text_statistic.repositories;

import com.andry.fedosov.web_text_statistic.entity.Text;
import dao.TextFileDAO;

import entity.TextFile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface TextFileEntityRepository extends CrudRepository<Text, Integer>  {


    @Override
    Text save(Text entity);

    Text getById (int id);

    @Query(value = "SELECT MAX(id) FROM text_stat",nativeQuery = true)
    Integer getLastId();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE text_stat SET " +
            "longest_word = (SELECT l.longest_word FROM line AS l WHERE l.file_id = :id AND l.longest_word IS NOT NULL ORDER BY char_length(l.longest_word) DESC LIMIT 1)," +
            "shortest_word = (SELECT l.shortest_word FROM line AS l WHERE l.file_id = :id AND l.shortest_word IS NOT NULL ORDER BY char_length(l.shortest_word) LIMIT 1)," +
            "text_length = (SELECT sum(char_length(l.line)) FROM line AS l WHERE l.file_id = :id)," +
            "average_word_length = (SELECT avg(l.average_word_length) FROM line AS l WHERE l.file_id = :id AND l.average_word_length !=0) WHERE id = :id",
            nativeQuery = true)
    void updateTextStatisticFromLines(@Param("id") int id);


    List<Text> findAll();

    long count();


    @Query(value = "select * from line inner join text_stat group by id having count(file_id) >=:minimumLines ",nativeQuery = true)
    List<Text> textHaveMoreLines(@Param("minimumLines") int minimumLines);

    @Query(value ="select count(t.id) from (select t.id as id from line l inner join text_stat t on l.file_id = t.id group by (t.id) having count(l.file_id) >=?1) t",nativeQuery = true)
    long countOfFilteredTexts(int lines);


}
