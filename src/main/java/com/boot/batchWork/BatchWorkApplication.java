package com.boot.batchWork;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 배치 기능 활성화
 */
@EnableBatchProcessing
@SpringBootApplication
public class BatchWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchWorkApplication.class, args);
	}

}
