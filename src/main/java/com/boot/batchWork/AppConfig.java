package com.boot.batchWork;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class AppConfig {
    /* JPA setting */
    @PersistenceContext(unitName = "primaryEntityManager")
    private EntityManager primaryEntityManager;

    @PersistenceContext(unitName = "secondEntityManager")
    private EntityManager secondEntityManager;

    @PersistenceContext(unitName = "thirdEntityManager")
    private EntityManager thirdEntityManager;

    @PersistenceContext(unitName = "forthEntityManager")
    private EntityManager forthEntityManager;

//    /* QueryDsl setting */
//    @Primary // ?
//    @Bean
//    public JPAQueryFactory primaryQueryFactory() {
//        return new JPAQueryFactory(primaryEntityManager);
//    }
//
//    @Bean
//    @Qualifier("SecondQueryFactory")
//    public JPAQueryFactory rawDataQueryFactory() {
//        return new JPAQueryFactory(secondEntityManager);
//    }

}
