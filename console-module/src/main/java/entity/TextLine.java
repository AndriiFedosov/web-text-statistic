package entity;


import java.util.Objects;

public class TextLine {

    private int numberLine;

    private TextFile file;

    private String line;

    private String longestWord;

    private String shortestWord;

    private int averageWordLength;

    public TextLine(int numberLine,  String line, String longestWord, String shortestWord, int averageWordLength) {
        this.numberLine = numberLine;
        this.line = line;
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.averageWordLength = averageWordLength;
    }

    public TextLine() {
    }

    public int getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(int numberLine) {
        this.numberLine = numberLine;
    }

    public TextFile getFile() {
        return file;
    }

    public void setFile(TextFile file) {
        this.file = file;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getLongestWord() {
        return longestWord;
    }

    public void setLongestWord(String longestWord) {
        this.longestWord = longestWord;
    }

    public String getShortestWord() {
        return shortestWord;
    }

    public void setShortestWord(String shortestWord) {
        this.shortestWord = shortestWord;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextLine textLine = (TextLine) o;
        return numberLine == textLine.numberLine &&
                averageWordLength == textLine.averageWordLength &&
                Objects.equals(file, textLine.file) &&
                Objects.equals(line, textLine.line) &&
                Objects.equals(longestWord, textLine.longestWord) &&
                Objects.equals(shortestWord, textLine.shortestWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(numberLine, file, line, longestWord, shortestWord, averageWordLength);
    }

    @Override
    public String toString() {
        return "TextLine{" +
                "numberLine=" + numberLine +
                ", file=" + file +
                ", line='" + line + '\'' +
                ", longestWord='" + longestWord + '\'' +
                ", shortestWord='" + shortestWord + '\'' +
                ", averageWordLength=" + averageWordLength +
                '}';
    }
}
