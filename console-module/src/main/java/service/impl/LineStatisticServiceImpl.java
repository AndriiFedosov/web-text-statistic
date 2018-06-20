package service.impl;

import dao.TextLineDAO;
import entity.TextLine;
import service.LineStatisticService;

public class LineStatisticServiceImpl implements LineStatisticService {

    private TextLineDAO dao;

    public LineStatisticServiceImpl(TextLineDAO dao) {
        this.dao = dao;
    }

    @Override
    public TextLine save(TextLine textLine) {
        return dao.save(textLine);
    }

    @Override
    public TextLine getById(int textId, int numOfLine) {
        return dao.getById(textId,numOfLine);
    }
}
