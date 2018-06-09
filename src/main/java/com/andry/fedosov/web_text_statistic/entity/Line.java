package com.andry.fedosov.web_text_statistic.entity;

import entity.TextFile;
import entity.TextLine;


import javax.persistence.*;

@Entity
@Table(name = "line")
public class Line extends TextLine {

    public Line(int numberLine, String line, String longestWord, String shortestWord, int averageWordLength) {
        super(numberLine, line, longestWord, shortestWord, averageWordLength);
    }
    public Line() {
        super();
    }


    @Id
    @Column(name = "line_number" ,columnDefinition = "int")
    @Override
    public int getNumberLine() {
        return super.getNumberLine();
    }
    @Override
    public void setNumberLine(int numberLine) {
        super.setNumberLine(numberLine);
    }

    @ManyToOne(targetEntity = Text.class)
    @Override
    public TextFile getFile() {
        return super.getFile();
    }
    @Override
    public void setFile(TextFile file) {
        super.setFile(file);
    }


    @Column(name = "line" ,columnDefinition = "text")
    @Override
    public String getLine() {
        return super.getLine();
    }
    @Override
    public void setLine(String line) {
        super.setLine(line);
    }

    @Column(name = "longest_word" , columnDefinition = "text")
    @Override
    public String getLongestWord() {
        return super.getLongestWord();
    }
    @Override
    public void setLongestWord(String longestWord) {
        super.setLongestWord(longestWord);
    }


    @Column(name = "shortest_word", columnDefinition = "text")
    @Override
    public String getShortestWord() {
        return super.getShortestWord();
    }
    @Override
    public void setShortestWord(String shortestWord) {
        super.setShortestWord(shortestWord);
    }


    @Column(name = "average_word_length" , columnDefinition = "int")
    @Override
    public int getAverageWordLength() {
        return super.getAverageWordLength();
    }
    @Override
    public void setAverageWordLength(int averageWordLength) {
        super.setAverageWordLength(averageWordLength);
    }

}
