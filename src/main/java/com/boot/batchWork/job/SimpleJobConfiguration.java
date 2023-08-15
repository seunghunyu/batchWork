package com.boot.batchWork.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
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
//            return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build(); //simpleJob 이란 이름의 BatchJob 생성
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep2(null))
                .next(simpleStep3(null))
                .build(); //simpleJob 이란 이름의 BatchJob 생성
    }

    @Bean
    public Step simpleStep1() {  //sipmleStep1 이란 BatchStep을 생성
        //Step안에서 수행될 기능들을 명시 , Tasklet은 Step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용
        return stepBuilderFactory.get("simpleStep1").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step1");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestData]}") String requestDate) {
        //Job Parameter 는 Spring Batch가 실행 될 떄 외부에서 받을 수 있는 파라미터
        // 특정 날짜를 넘기면 해당 데이터로 조회/가공/입력등의 작업을 할 수 있다.
        /**  BATCH_JOB_INSTANCE 테이블에 파라미터 값만 바꾸어서 계속해서 실행시켜주면 새로운 JobInstance가 생김
         *   같은 Job 중복되는 파라미터로 실행시에 생성 안됨 (JobInstanceAlreadyCompleteException)
         *   친절하게 방법도 뱉어줌
         *   A job instance already exists and is complete for parameters={requestData=20230815}.
         *   If you want to run this job again, change the parameters.
         */

        return stepBuilderFactory.get("simpleStep2").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step2");
            log.info(">>>>>requestDate = {}", requestDate);
            return RepeatStatus.FINISHED;
//          throw new IllegalArgumentException("Step2에서 실패");
            /** Exception 발생시 BATCH_JOB_EXECUTION 테이블에 해당 Job의 Status로 FAILED 로 처리
             *  Exception 주석처리 후 수행시 해당 JOB_INSTANCE_ID인 4로 COMPLETED 상태로 추가
             *  (물론 상태값이 실패했었기 때문에 파라미터 변경안하고 수행해도 이상없이 추가 된다.)
             *  SpringBatch는 동일한 Job Parameter로 성공한 기록이 있을때만 재수행이 안된다.
             *  BATCH_JOB_EXECUTION_PARAMS 테이블 조회시 넘겨줬던 파라미터 정보 확인 가능
             */
        })).build();
    }
    @Bean
    @JobScope
    public Step simpleStep3(@Value("#{jobParameters[requestData]}") String requestDate) {

        return stepBuilderFactory.get("simpleStep3").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step3");
            log.info(">>>>>requestDate = {}", requestDate);
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