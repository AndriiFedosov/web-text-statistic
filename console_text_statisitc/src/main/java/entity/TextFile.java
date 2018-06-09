package entity;


import java.util.Objects;

public class TextFile {

    private int id;

    private String longestWord;

    private String shortestWord;

    private long textLength;

    private int averageLengthWord;

    public TextFile() {
    }

    public TextFile(int id) {
        this.id = id;
    }

    public TextFile(int id, String longestWord, String shortestWord, long textLength, int averageLengthWord) {
        this.id = id;
        this.longestWord = longestWord;
        this.shortestWord = shortestWord;
        this.textLength = textLength;
        this.averageLengthWord = averageLengthWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getTextLength() {
        return textLength;
    }

    public void setTextLength(long textLength) {
        this.textLength = textLength;
    }

    public int getAverageLengthWord() {
        return averageLengthWord;
    }

    public void setAverageLengthWord(int averageLengthWord) {
        this.averageLengthWord = averageLengthWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextFile textFile = (TextFile) o;
        return id == textFile.id &&
                textLength == textFile.textLength &&
                averageLengthWord == textFile.averageLengthWord &&
                Objects.equals(longestWord, textFile.longestWord) &&
                Objects.equals(shortestWord, textFile.shortestWord);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, longestWord, shortestWord, textLength, averageLengthWord);
    }

    @Override
    public String toString() {
        return "TextFile{" +
                "id=" + id +
                ", longestWord='" + longestWord + '\'' +
                ", shortestWord='" + shortestWord + '\'' +
                ", textLength=" + textLength +
                ", averageLengthWord=" + averageLengthWord +
                '}';
    }
}
