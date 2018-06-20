package datasource;


import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;

public class DataSourceCreatorTest {
    @Test
    public void createDataSource(){
        DataSource dataSource = DataSourceCreator.getSource();

        Assert.assertNotNull(dataSource);
        Assert.assertEquals(dataSource.hashCode(),
                            DataSourceCreator.getSource().hashCode());
    }
}
