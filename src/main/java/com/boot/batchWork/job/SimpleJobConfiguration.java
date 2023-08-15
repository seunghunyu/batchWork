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
//            return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build(); //simpleJob �̶� �̸��� BatchJob ����
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep2(null))
                .next(simpleStep3(null))
                .build(); //simpleJob �̶� �̸��� BatchJob ����
    }

    @Bean
    public Step simpleStep1() {  //sipmleStep1 �̶� BatchStep�� ����
        //Step�ȿ��� ����� ��ɵ��� ��� , Tasklet�� Step�ȿ��� ���Ϸ� ����� Ŀ������ ��ɵ��� �����Ҷ� ���
        return stepBuilderFactory.get("simpleStep1").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step1");
            return RepeatStatus.FINISHED;
        })).build();
    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestData]}") String requestDate) {
        //Job Parameter �� Spring Batch�� ���� �� �� �ܺο��� ���� �� �ִ� �Ķ����
        // Ư�� ��¥�� �ѱ�� �ش� �����ͷ� ��ȸ/����/�Էµ��� �۾��� �� �� �ִ�.
        /**  BATCH_JOB_INSTANCE ���̺� �Ķ���� ���� �ٲپ ����ؼ� ��������ָ� ���ο� JobInstance�� ����
         *   ���� Job �ߺ��Ǵ� �Ķ���ͷ� ����ÿ� ���� �ȵ� (JobInstanceAlreadyCompleteException)
         *   ģ���ϰ� ����� �����
         *   A job instance already exists and is complete for parameters={requestData=20230815}.
         *   If you want to run this job again, change the parameters.
         */

        return stepBuilderFactory.get("simpleStep2").tasklet(((contribution, chunkContext) -> {
            log.info(">>>>>This is step2");
            log.info(">>>>>requestDate = {}", requestDate);
            return RepeatStatus.FINISHED;
//          throw new IllegalArgumentException("Step2���� ����");
            /** Exception �߻��� BATCH_JOB_EXECUTION ���̺� �ش� Job�� Status�� FAILED �� ó��
             *  Exception �ּ�ó�� �� ����� �ش� JOB_INSTANCE_ID�� 4�� COMPLETED ���·� �߰�
             *  (���� ���°��� �����߾��� ������ �Ķ���� ������ϰ� �����ص� �̻���� �߰� �ȴ�.)
             *  SpringBatch�� ������ Job Parameter�� ������ ����� �������� ������� �ȵȴ�.
             *  BATCH_JOB_EXECUTION_PARAMS ���̺� ��ȸ�� �Ѱ���� �Ķ���� ���� Ȯ�� ����
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
 * Spring Batch�� ��Ÿ ������ ���̺�� �ʿ� ������ ������� ������ ����
 *
 * ������ ������ Job�� � �͵��� �ִ���
 * �ֱ� ������ Batch Parameter�� ��͵��� �ְ�, ������ Job�� ��͵��� �ִ���
 * �ٽ� �����Ѵٸ� ��� ���� �����ϸ� ����
 * � Job�� � Step���� �־���, Step�� �� ������ Step�� ������ Step���� ��͵��� �ִ���
 * (h2 ���� ��Ÿ ���̺�� �ڵ������������� �Ϲ����� RDB ���� ����ڰ� ���� ����������)
 *
 */