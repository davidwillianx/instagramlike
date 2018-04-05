package br.com.instagramlike.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.instagramlike.models.repositories")
public class Persistence {


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws IOException {

        LocalContainerEntityManagerFactoryBean entityManager;
        entityManager = new LocalContainerEntityManagerFactoryBean();


        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setDataSource(dataSource());
        entityManager.setPackagesToScan("br.com.instagramlike.models.domains");

        entityManager.setJpaProperties(additionalProperties());


        return entityManager;

    }



    @Bean
    public DataSource dataSource() throws IOException {

        Resource dbResource;
        Properties dbProperties;
        DriverManagerDataSource dataSource;

        dbResource = new ClassPathResource("./application.properties");
        dbProperties = PropertiesLoaderUtils.loadProperties(dbResource);

        dataSource = new DriverManagerDataSource(
                dbProperties.getProperty("url"),
                dbProperties.getProperty("username"),
                dbProperties.getProperty("password")
        );

        dataSource.setDriverClassName("org.postgresql.Driver");


        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){

        JpaTransactionManager jpaTransactionManager;
        jpaTransactionManager = new JpaTransactionManager();


        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);


        return jpaTransactionManager;
    }


    public Properties additionalProperties(){

        Properties additionalProperties;
        additionalProperties = new Properties();

        additionalProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        additionalProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        additionalProperties.setProperty("hibernate.show_sql", "true");

        return additionalProperties;
    }


}
