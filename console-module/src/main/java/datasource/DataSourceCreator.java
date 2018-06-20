package datasource;




import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceCreator {

    private static MysqlDataSource source;

    public static DataSource getSource() {
        if(source == null){
            synchronized (DataSourceCreator.class){
                if(source == null){
                    Properties properties = new Properties();

                    try (InputStream loadProps = DataSourceCreator.class.getClassLoader().getResourceAsStream("db.properties")) {
                        properties.load(loadProps);
                    } catch (IOException e) {
                        throw new IllegalStateException("Couldn't initialize DataSource");
                    }

                    source = new MysqlDataSource();
                    source.setURL(properties.getProperty("db.url"));
                    source.setUser(properties.getProperty("db.user"));
                    source.setPassword(properties.getProperty("db.password"));;
                }
            }
        }
        return source;
    }
}
