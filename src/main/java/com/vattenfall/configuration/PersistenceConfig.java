
package com.vattenfall.configuration;

import com.vattenfall.services.ParkingService;
import com.vattenfall.services.ParkingServiceImpl;
import com.vattenfall.services.UserService;
import com.vattenfall.services.UserServiceImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * Created by amoss on 17.01.14.
 */

@Configuration
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@EnableJpaRepositories("com.vattenfall.repository")
@EnableTransactionManagement
public class PersistenceConfig {
    // TODO make those properties work with @Value
    // @Value("${db.driver}")
    // private String DATABASE_DRIVER;
    //@Value("${db.username}")
    //private String DATABASE_USERNAME;
    //@Value("${db.password}")
    //private String DATABASE_PASSWORD;
    //@Value("${hibernate.dialect}")
    //private String HIBERNATE_DIALECT;
    //@Value("${hibernate.show.sql}")
    //private String HIBERNATE_SHOW_SQL;
    //@Value("${entitymanager.packages.to.scan}")
    //private String ENTITYMANAGER_PACKAGES_TO_SCAN;
    //@Value("${db.url}")
    //private String DATABASE_URL;

    private String DATABASE_DRIVER = "org.hsqldb.jdbcDriver";
    private String DATABASE_USERNAME = "sa";
    private String DATABASE_PASSWORD = "";
    private String HIBERNATE_DIALECT = "org.hibernate.dialect.HSQLDialect";
    private String HIBERNATE_SHOW_SQL = "true";
    private String HIBERNATE_SHOW_FORMAT_SQL = "true";
    private String HIBERNATE_USE_SQL_COMMENTS = "true";
    private String ENTITYMANAGER_PACKAGES_TO_SCAN = "com.vattenfall.model";
    private String DATABASE_URL = "jdbc:hsqldb:file:mydb";


    private String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private String PROPERTY_NAME_HIBERNATE_SHOW_FORMAT_SQL = "hibernate.format_sql";
    private String PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";

    private String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DATABASE_DRIVER);
        basicDataSource.setUsername(DATABASE_USERNAME);
        basicDataSource.setPassword(DATABASE_PASSWORD);
        basicDataSource.setUrl(DATABASE_URL);
        return basicDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, HIBERNATE_DIALECT);
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, HIBERNATE_SHOW_SQL);
//      properties.put(PROPERTY_NAME_HIBERNATE_SHOW_FORMAT_SQL, HIBERNATE_SHOW_FORMAT_SQL);
        properties.put(PROPERTY_NAME_HIBERNATE_USE_SQL_COMMENTS, HIBERNATE_USE_SQL_COMMENTS);
        properties.put("hibernate.hbm2ddl.auto", "create");
/*      hbm2dll.auto properties
        validate: validate the schema, makes no changes to the database.
        update: update the schema.
        create: creates the schema, destroying previous data.
        create-drop: drop the schema at the end of the session.*/
        return properties;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}




