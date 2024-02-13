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
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.boot.batchWork.repository.dsdmt", // 첫번째 DB가 있는 패키지(폴더) 경로
        entityManagerFactoryRef = "primaryEntityManagerFactory", // EntityManager의 이름
        transactionManagerRef = "primaryTransactionManager" // 트랜잭션 매니저의 이름
)
public class DataSourceConfig1 {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource-dsdmt") // application.properties에 작성된 DB와 관련된 설정 값들의 접두사
    public DataSourceProperties primaryDatasourceProperties() {
        return new DataSourceProperties();
    }
//    @Autowired
//    private Environment env;

    @Bean
    @Primary
//    @ConfigurationProperties("spring.datasource-dsdmt.configuration") // DB와 관련된 설정값들의 접두사에 .configuration을 붙여준다.
    @ConfigurationProperties(prefix = "spring.datasource-dsdmt")
    public DataSource primaryDatasource() {
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", env.getProperty("username"));
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", env.getProperty("driver-class-name"));
//        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", env.getProperty("url"));
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("driver-class-name"));
//        dataSource.setUrl(env.getProperty("url"));
//        dataSource.setUsername(env.getProperty("username"));
//        dataSource.setPassword(env.getProperty("password"));
//        return dataSource;
//        DataSourceProperties dataSourceProperties = primaryDatasourceProperties();
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", primaryDatasourceProperties().getDriverClassName());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", primaryDatasourceProperties().getUsername());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", primaryDatasourceProperties().getUrl());
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@primaryDataSource = {}", primaryDatasourceProperties().getPassword());
//        dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//        return (DataSource) dataSourceProperties;

        return primaryDatasourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        DataSource dataSource = primaryDatasource();
        return builder
                .dataSource(dataSource)
                .packages("com.boot.batchWork.data.dsdmt") // 첫번째 DB와 관련된 엔티티들이 있는 패키지(폴더) 경로
                .persistenceUnit("primaryEntityManager")
                .build();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(
            final @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory
    ) {
        return new JpaTransactionManager(primaryEntityManagerFactory.getObject());
    }

//    @Bean(name = "primaryTransactionManager")
//    @Primary
//    public PlatformTransactionManager primaryTransactionManager(
//            final @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryContainerEntityManagerFactoryBean
//    ) {
//        return new JpaTransactionManager(primaryContainerEntityManagerFactoryBean.getObject());
//    }
}
