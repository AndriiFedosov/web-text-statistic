package com.andry.fedosov.web_text_statistic.repositories;

import com.andry.fedosov.web_text_statistic.entity.Line;
import entity.TextLine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface TextLineEntityRepository extends CrudRepository<Line,Integer> {

    Line save(Line line);

    @Query(value = "SELECT MAX(line_number) FROM line",nativeQuery = true)
    Integer getLastId();

    Line getByNumberLineAndFileId(int numberLine, int file_id);

    @Query(value = "select * from line where file_id = :id ",nativeQuery = true)
    List<Line> getAllLines(@Param("id") int id);

    @Query(value = "select count(line_number) FROM line where file_id =:id",nativeQuery = true)
    long countByFileId(@Param("id") int id);

    @Query(value = "SELECT MAX(file_id) FROM line",nativeQuery = true)
    int getLastFileId();

}
