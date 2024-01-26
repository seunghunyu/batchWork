package com.boot.batchWork.config.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.boot.batchWork.repository.meta",
        entityManagerFactoryRef = "forthEntityManagerFactory",
        transactionManagerRef = "forthTransactionManager"
)
public class DataSourceConfig4 {
    @Bean
    @ConfigurationProperties("spring.forth-datasource")
    public DataSourceProperties forthDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.forth-datasource.configuration")
    public DataSource forthDatasource() {
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
            final @Qualifier("forthEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}