package service;

import entity.TextLine;

public interface LineStatisticService {
    TextLine save(TextLine textLine);

    TextLine getById(int textId, int numOfLine);
}
