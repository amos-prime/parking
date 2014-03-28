package services;

import com.vattenfall.configuration.PersistenceConfig;
import com.vattenfall.configuration.ServicesConfig;
import com.vattenfall.services.ServiceFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertTrue;

/**
 * Created by amoss on 26.03.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, ServicesConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ServiceFacadeTest {

    @Autowired
    private ServiceFacade serviceFacade;

    @Test
    public void beanCreationTest() {
        assertTrue(serviceFacade != null);
    }

}
