package com.boot.batchWork.Controller;

import com.boot.batchWork.data.redis.CacheData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    StringRedisTemplate stringRedisTemplate;


    @PostMapping("/exist")
    public ResponseEntity<String> getExistYn(@RequestBody CacheData cacheData){

        ResponseEntity<String> entity = null;

        try{
            log.info("Request CacheName = {}, Field = {}", cacheData.getCacheName(), cacheData.getField());
            entity = new ResponseEntity<String>("OK", HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;

    }
}
