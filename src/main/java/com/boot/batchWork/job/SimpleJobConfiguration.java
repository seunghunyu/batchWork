package com.boot.batchWork.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Spring Batch에서 Job은 하나의 배치 작업단위
 * Job -> [Step, Step ,...]
 *     -> {tasklet ,  (Reader, Processor, Writer)}
 *
 *     Job은 여러개의 Step으로 존재 Step은 tasklet 과 Reader와 Processor, Writer 를 포함
 */
@Slf4j
@RequiredArgsConstructor
@Configuration //SpringBatch의 모든 Job은 @Configuration을 등록해서 사용
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob(){
            return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build(); //simpleJob 이란 이름의 BatchJob 생성
    }
    @Bean
    public Step simpleStep1() {  //sipmleStep1 이란 BatchStep을 생성
        //Step안에서 수행될 기능들을 명시 , Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용
        return stepBuilderFactory.get("simpleStep1").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step1");
            return RepeatStatus.FINISHED;
        })).build();
    }
}
/**
 * Spring Batch는 메타 데이터 테이블들 필요 다음의 내용들을 가지고 있음
 *
 * 이전에 실행한 Job이 어떤 것들이 있는지
 * 최근 실패한 Batch Parameter가 어떤것들이 있고, 성공한 Job은 어떤것들이 있는지
 * 다시 실행한다면 어디서 부터 시작하면 될지
 * 어떤 Job에 어떤 Step들이 있었고, Step들 중 성공한 Step과 실패한 Step들은 어떤것들이 있는지
 * (h2 사용시 메타 테이블들 자동생성해주지만 일반적인 RDB 사용시 사용자가 직접 만들어줘야함)
 *
 */