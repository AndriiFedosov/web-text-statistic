package com.andry.fedosov.web_text_statistic.entity;

import entity.TextFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "text_stat")
public class Text extends TextFile {

    public Text() {
    }

    public Text(int id) {
        super(id);
    }

    public Text(int id, String longestWord, String shortestWord, long textLength, int averageLengthWord) {
        super(id, longestWord, shortestWord, textLength, averageLengthWord);
    }


    @Id
    @Column(name = "id")
    @Override
    public int getId() {
        return super.getId();
    }
    @Override
    public void setId(int id) {
        super.setId(id);
    }


    @Column(name = "longest_word")
    @Override
    public String getLongestWord() {
        return super.getLongestWord();
    }
    @Override
    public void setLongestWord(String longestWord) {
        super.setLongestWord(longestWord);
    }


    @Column(name = "shortest_word" )
    @Override
    public String getShortestWord() {
        return super.getShortestWord();
    }
    @Override
    public void setShortestWord(String shortestWord) {
        super.setShortestWord(shortestWord);
    }


    @Column(name = "text_length",columnDefinition = "bigint")
    @Override
    public long getTextLength() {
        return super.getTextLength();
    }
    @Override
    public void setTextLength(long textLength) {
        super.setTextLength(textLength);
    }


    @Column(name = "average_word_length" )
    @Override
    public int getAverageLengthWord() {
        return super.getAverageLengthWord();
    }
    @Override
    public void setAverageLengthWord(int averageLengthWord) {
        super.setAverageLengthWord(averageLengthWord);
    }


}
