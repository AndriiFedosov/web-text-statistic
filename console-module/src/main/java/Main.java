
import dao.impl.TextFileDAOImpl;
import dao.impl.TextLineDAOImpl;
import datasource.DataSourceCreator;
import entity.TextFile;
import service.impl.LineStatisticServiceImpl;
import service.impl.TextStatisticServiceImpl;
import utils.TextStatisticCreator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    private static TextStatisticCreator creator;

    public Main() throws IOException {
       TextStatisticServiceImpl textStatisticService = new TextStatisticServiceImpl(new TextFileDAOImpl(DataSourceCreator.getSource()));
       LineStatisticServiceImpl lineStatisticService = new LineStatisticServiceImpl(new TextLineDAOImpl(DataSourceCreator.getSource()));
       creator = new TextStatisticCreator(textStatisticService,lineStatisticService);
    }

    public static void main(String[] args) throws IOException {
//        if(args.length == 0){
//            throw new IllegalStateException("We cant read path to file");
//        }
        new Main().run("F:\\InteleJ_IDEA_Project\\console_text_statistic\\src\\main\\resources\\second");
    }

    private void run(String path) {
        if (new File(path).isDirectory()) {
            List<TextFile> statistics = creator.getStatisticFromAllFiles(path);
            for (TextFile text : statistics) {
                showStatistic(text);
            }
        } else {
            showStatistic(creator.createStatisticInFile(path));
        }
    }
    private void showStatistic(TextFile text){
        System.out.println("File statistic id:  "+ text.getId());
        System.out.println("Text length is : "+ text.getTextLength());
        System.out.println("Longest word in text is : "+ text.getLongestWord());
        System.out.println("Shortest word in text is : "+ text.getShortestWord());
        System.out.println("Average length of all words in text is : "+ text.getAverageLengthWord()+"\n");
    }
}
