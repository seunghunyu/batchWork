package com.boot.batchWork.util;

import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@ToString
@Setter
@ConfigurationProperties(prefix = "demo.test")
@Configuration
public class TestConfig {
    private String userId;
    private String password;
    private List<String> sites;
    private Option option;

    @ToString
    @Setter
    public static class Option{
        private boolean admin;
        private boolean view;
    }

}
