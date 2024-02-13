package com.boot.batchWork.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Slf4j
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.boot.batchWork.repository.rebm",
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondTransactionManager"
)
public class DataSourceConfig2 {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-rebm")
    public DataSourceProperties secondDatasourceProperties() {
        return new DataSourceProperties();
    }
//    @Autowired
    private Environment env;

    @Bean
//    @ConfigurationProperties("spring.datasource-rebm.configuration")
    @ConfigurationProperties(prefix = "spring.datasource-rebm")
    public DataSource secondDatasource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("username"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@secondDatasource = {}", secondDatasourceProperties().getDriverClassName());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@secondDatasource = {}", secondDatasourceProperties().getUsername());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@secondDatasource = {}", secondDatasourceProperties().getUrl());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@secondDatasource = {}", secondDatasourceProperties().getPassword());
        return secondDatasourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = secondDatasource();
        return builder
                .dataSource(dataSource)
                .packages("com.boot.batchWork.data.rebm")
                .persistenceUnit("secondEntityManager")
                .build();
    }

    @Bean(name = "secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(
            final @Qualifier("secondEntityManagerFactory") LocalContainerEntityManagerFactoryBean secondContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(secondContainerEntityManagerFactoryBean.getObject());
    }
}
