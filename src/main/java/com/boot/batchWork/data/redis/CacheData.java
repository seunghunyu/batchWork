package com.boot.batchWork.data.redis;

import lombok.Data;

@Data
public class CacheData {
    private String cacheName;
    private String tableName;
    private String realFlowId;
    private String workDt;
    private String field;
}
