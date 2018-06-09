package dao;

import entity.TextFile;

public interface TextFileDAO {

    TextFile save(TextFile textFile);

    TextFile getById(int id);

    TextFile createStatFromLines(int id);
}
