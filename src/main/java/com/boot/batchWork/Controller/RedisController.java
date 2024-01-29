package com.boot.batchWork.Controller;

import com.boot.batchWork.data.redis.CacheData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    @Qualifier("stringRedisClusterTemplate")
    StringRedisTemplate stringRedisClusterTemplate;

    @Autowired
    @Qualifier("stringRedisSingleTemplate")
    StringRedisTemplate stringRedisSingleTemplate;

    @Autowired
    @Qualifier("redisClusterTemplate")
    RedisTemplate redisClusterTemplate;

    @Autowired
    @Qualifier("redisSingleTemplate")
    RedisTemplate redisSingleTemplate;

    @PostMapping("/exist")
    public ResponseEntity<String> getExistYn(@RequestBody CacheData cacheData){

        ResponseEntity<String> entity = null;
        String cache = "";
        String field = "";
        try{
            log.info("Request CacheName = {}, Field = {}", cacheData.getCacheName(), cacheData.getField());
            cache = cacheData.getTableName() + "#" + cacheData.getRealFlowId() + "#" + cacheData.getWorkDt();
            field = cacheData.getField();

            String data = (String)stringRedisClusterTemplate.opsForHash().get(cache, field);


            if(data == null || data.equals("")){
                log.info("data is null");
                data = "Data is empty";
            }else{
                log.info("@@@@@@@Redis get Data = {}", data);
            }

            entity = new ResponseEntity<String>(data, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;

    }
}
