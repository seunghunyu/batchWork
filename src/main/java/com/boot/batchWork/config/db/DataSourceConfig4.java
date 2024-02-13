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
        basePackages = "com.boot.batchWork.repository.meta",
        entityManagerFactoryRef = "forthEntityManagerFactory",
        transactionManagerRef = "forthTransactionManager"
)
public class DataSourceConfig4 {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-meta")
    public DataSourceProperties forthDatasourceProperties() {
        return new DataSourceProperties();
    }
//    @Autowired
//    private Environment env;

    @Bean
//    @ConfigurationProperties("spring.datasource-meta.configuration")
    @ConfigurationProperties(prefix = "spring.datasource-meta")
    public DataSource forthDatasource() {
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@forth DataSource = {}", env.getProperty("username"));
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("username"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@forthDatasource = {}", forthDatasourceProperties().getDriverClassName());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@forthDatasource = {}", forthDatasourceProperties().getUsername());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@forthDatasource = {}", forthDatasourceProperties().getUrl());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@forthDatasource = {}", forthDatasourceProperties().getPassword());
        return forthDatasourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "forthEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean forthEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = forthDatasource();
        return builder
                .dataSource(dataSource)
                .packages("com.boot.batchWork.data.meta")
                .persistenceUnit("forthEntityManager")
                .build();
    }

    @Bean(name = "forthTransactionManager")
    public PlatformTransactionManager forthTransactionManager(
            final @Qualifier("forthEntityManagerFactory") LocalContainerEntityManagerFactoryBean forthContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(forthContainerEntityManagerFactoryBean.getObject());
    }
}
