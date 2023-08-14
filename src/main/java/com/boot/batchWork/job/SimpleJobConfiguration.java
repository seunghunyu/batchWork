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
 * Spring Batch���� Job�� �ϳ��� ��ġ �۾�����
 * Job -> [Step, Step ,...]
 *     -> {tasklet ,  (Reader, Processor, Writer)}
 *
 *     Job�� �������� Step���� ���� Step�� tasklet �� Reader�� Processor, Writer �� ����
 */
@Slf4j
@RequiredArgsConstructor
@Configuration //SpringBatch�� ��� Job�� @Configuration�� ����ؼ� ���
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob(){
            return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build(); //simpleJob �̶� �̸��� BatchJob ����
    }
    @Bean
    public Step simpleStep1() {  //sipmleStep1 �̶� BatchStep�� ����
        //Step�ȿ��� ����� ��ɵ��� ��� , Tasklet�� Step�ȿ��� ���Ϸ� ����� Ŀ������ ��ɵ��� �����Ҷ� ���
        return stepBuilderFactory.get("simpleStep1").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step1");
            return RepeatStatus.FINISHED;
        })).build();
    }
}
/**
 * Spring Batch�� ��Ÿ ������ ���̺�� �ʿ� ������ ������� ������ ����
 *
 * ������ ������ Job�� � �͵��� �ִ���
 * �ֱ� ������ Batch Parameter�� ��͵��� �ְ�, ������ Job�� ��͵��� �ִ���
 * �ٽ� �����Ѵٸ� ��� ���� �����ϸ� ����
 * � Job�� � Step���� �־���, Step�� �� ������ Step�� ������ Step���� ��͵��� �ִ���
 * (h2 ���� ��Ÿ ���̺�� �ڵ������������� �Ϲ����� RDB ���� ����ڰ� ���� ����������)
 *
 */