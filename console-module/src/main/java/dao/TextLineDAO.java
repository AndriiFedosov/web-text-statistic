package dao;

import entity.TextLine;

public interface TextLineDAO {

    TextLine save(TextLine textLine);

    TextLine getById(int textId, int numOfLine);
}
