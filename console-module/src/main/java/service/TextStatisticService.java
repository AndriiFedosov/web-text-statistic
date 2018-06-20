package service;

import entity.TextFile;

public interface TextStatisticService {
    TextFile save(TextFile textFile);

    TextFile getById(int id);

    TextFile createStatFromLines(TextFile textFile);
}
