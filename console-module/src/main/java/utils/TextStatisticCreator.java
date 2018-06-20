package utils;

import entity.TextFile;
import entity.TextLine;
import org.apache.commons.io.FileUtils;
import service.LineStatisticService;
import service.impl.LineStatisticServiceImpl;
import service.impl.TextStatisticServiceImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextStatisticCreator {

    private TextStatisticServiceImpl textStatisticService;
    private LineStatisticService lineStatisticService;

    public TextStatisticCreator(TextStatisticServiceImpl textStatisticService, LineStatisticService lineStatisticService) {
        this.textStatisticService = textStatisticService;
        this.lineStatisticService = lineStatisticService;
    }

    public TextFile createStatisticInFile(String path){
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("We can`t read this file",e);
        }
        TextFile text = new TextFile(new Random().nextInt(25000000));
        text = textStatisticService.save(text);
            int number = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                TextLine textLine = createStatisticInLine(line);
                textLine.setNumberLine(number);
                textLine.setFile(text);
                lineStatisticService.save(textLine);
                number++;
            }

        return textStatisticService.createStatFromLines(text);
    }

    public List<TextFile> getStatisticFromAllFiles(String dirPath) {
        List<File> files = new ArrayList<>(FileUtils.listFiles(new File(dirPath), null, true));

        return files.parallelStream()
                .map(file -> createStatisticInFile(file.getPath()))
                .collect(Collectors.toList());
    }

    public TextLine createStatisticInLine(String line){
        String temp = line.replaceAll(" ","");

        if(temp.isEmpty()) {
            return new TextLine(0, line, null, null, 0);
        }

        TextLine textLine = new TextLine();
        textLine.setLine(line);
        textLine.setLongestWord(getLongestWordWithPointChars(line));
        textLine.setShortestWord(getShortestWordWithPointChars(line));
        textLine.setAverageWordLength((int)Math.round(getAverageWordLength(line)));

        return textLine;
    }

    private String getLongestWordWithPointChars(String line){
        Scanner scanner = new Scanner(line);
        String word = scanner.next();
        while (scanner.hasNext()){
            String tempWord = scanner.next();
            if(word.length() < tempWord.length()){
                word = tempWord;
            }
        }
        return word;
    }

    private String getShortestWordWithPointChars(String line){
        Scanner scanner = new Scanner(line);
        String word = scanner.next();
        while (scanner.hasNext()){
            String tempWord = scanner.next();
            if(word.length() > tempWord.length()){
                word = tempWord;
            }
        }
        return word;
    }

    private int getAverageWordLength(String line){
        int countWords = 0;
        Scanner scanner = new Scanner(line);
        while (scanner.hasNext()){
            scanner.next();
            countWords +=1;
        }
        return line.replaceAll("^[A-Za-z0-9\"]","").length()/countWords;
    }


}
