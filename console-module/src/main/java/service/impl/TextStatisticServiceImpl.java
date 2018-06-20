package service.impl;

import dao.TextFileDAO;
import entity.TextFile;
import service.TextStatisticService;

public class TextStatisticServiceImpl implements TextStatisticService {
    private TextFileDAO dao;

    public TextStatisticServiceImpl(TextFileDAO dao) {
        this.dao = dao;
    }

    public TextStatisticServiceImpl() {
    }

    @Override
    public TextFile save(TextFile textFile) {
        return dao.save(textFile);
    }

    @Override
    public TextFile getById(int id) {
        return dao.getById(id);
    }

    @Override
    public TextFile createStatFromLines(TextFile textFile) {
        return dao.createStatFromLines(textFile.getId());
    }
}
