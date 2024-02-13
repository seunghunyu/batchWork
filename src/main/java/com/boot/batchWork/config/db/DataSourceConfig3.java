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
        basePackages = "com.boot.batchWork.repository.crm",
        entityManagerFactoryRef = "thirdEntityManagerFactory",
        transactionManagerRef = "thirdTransactionManager"
)
public class DataSourceConfig3 {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-crm")
    public DataSourceProperties thirdDatasourceProperties() {
        return new DataSourceProperties();
    }
    @Autowired
    private Environment env;

    @Bean
//    @ConfigurationProperties("spring.datasource-crm.configuration")
    @ConfigurationProperties(prefix = "spring.datasource-crm")
    public DataSource thirdDatasource() {
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@third DataSource = {}", env.getProperty("username"));
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("username"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@thirdDatasource = {}", thirdDatasourceProperties().getDriverClassName());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@thirdDatasource = {}", thirdDatasourceProperties().getUsername());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@thirdDatasource = {}", thirdDatasourceProperties().getUrl());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@thirdDatasource = {}", thirdDatasourceProperties().getPassword());
        return thirdDatasourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "thirdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = thirdDatasource();
        return builder
                .dataSource(dataSource)
                .packages("com.boot.batchWork.data.crm")
                .persistenceUnit("thirdEntityManager")
                .build();
    }

    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager(
            final @Qualifier("thirdEntityManagerFactory") LocalContainerEntityManagerFactoryBean thirdContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(thirdContainerEntityManagerFactoryBean.getObject());
    }
}
