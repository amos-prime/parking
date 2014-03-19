package persistence;

import com.vattenfall.configuration.PersistenceConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/**
 * Created by amoss on 17.01.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
public class DatabaseTest {

    @Autowired
    @Qualifier(value = "dataSource")
    private DataSource dataSource;
    @Autowired
    private JpaTransactionManager transactionManager;

    @Test
    public void dataSourceMemTest() throws SQLException {
        dataSource.getConnection();
    }

    @Test
    public void transactionManagerTest() {
        transactionManager.getDataSource();
    }
}
