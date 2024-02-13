package com.boot.batchWork.util;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
@PropertySource(value = "dbinfo.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "database")
@Data
public class GetYmlProperty {
//    private final List<DataBases> dataBases;
    public String[] brch;
}
