package com.boot.batchWork.Controller;

import com.boot.batchWork.data.redis.CacheData;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    /***
     * hash Data Scan
     * @param cacheData
     * @return
     */
    @PostMapping("/fieldscan")
    public ResponseEntity<JSONObject> getScanField(@RequestBody CacheData cacheData){

        ResponseEntity<JSONObject> entity = null;
        String cache = "";
        String field = "";
        SetOperations<String, String> stringStringSetOperations = stringRedisClusterTemplate.opsForSet();
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisClusterTemplate.opsForHash();
        JSONObject retJobj = new JSONObject();
        try{
            log.info("Request CacheName = {}, Field = {}", cacheData.getCacheName(), cacheData.getField());
            cache = cacheData.getTableName() + "#" + cacheData.getRealFlowId() + "#" + cacheData.getWorkDt();
            field = cacheData.getField();

            String data = "";
//            Cursor<String> cursor = stringStringSetOperations.scan(cache, ScanOptions.scanOptions().match("*").count(3).build());
            Cursor<Map.Entry<String, String>> cursor2 = stringObjectObjectHashOperations.scan(cache, ScanOptions.scanOptions().match("*").count(3).build());

            while(cursor2.hasNext()){
                Map.Entry<String, String> next = cursor2.next();
                //log.info("key = {}, value = {}", next.getKey(), next.getKey());
                retJobj.put(next.getKey(), next.getValue());
//                data += next.getKey() + "#";
            }

            data = retJobj.toJSONString();

//            while(cursor.hasNext()){
//                log.info("cursor = {}",cursor.next());
//                data += cursor.next() + "#";
//            }

            if(data == null || data.equals("")){
                log.info("data is null");
                data = "Data is empty";
            }else{
                log.info("@@@@@@@Redis get Data = {}", data);
            }


            entity = new ResponseEntity<JSONObject>(retJobj, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            retJobj.put("message",e.getMessage());
            entity = new ResponseEntity<JSONObject>(retJobj, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }
    /***
     * hash Data Scan
     * @param cacheData
     * @return
     */
    @PostMapping("/cachescan")
    public ResponseEntity<JSONObject> getScanCache(@RequestBody CacheData cacheData) {
        ResponseEntity<JSONObject> entity = null;
        String cache = "";
        String field = "";
        SetOperations<String, String> stringStringSetOperations = stringRedisClusterTemplate.opsForSet();
        HashOperations<String, String, String> stringObjectObjectHashOperations = stringRedisClusterTemplate.opsForHash();
        JSONObject retJobj = new JSONObject();
        try{
//            log.info("Request CacheName = {}, Field = {}", cacheData.getCacheName(), cacheData.getField());
//            cache = cacheData.getTableName() + "#" + cacheData.getRealFlowId() + "#" + cacheData.getWorkDt();
//            field = cacheData.getField();
            log.info("Request tableName = {}, Field = {}", cacheData.getTableName(), cacheData.getRealFlowId());
            String findPattKey = cacheData.getTableName() + "#" + cacheData.getRealFlowId();
            String data = "";
            ScanOptions scanOptions = ScanOptions.scanOptions().match(findPattKey +"*").count(100).build();

            Cursor<byte[]> keys = redisClusterTemplate.getConnectionFactory().getConnection().scan(scanOptions);
            int cnt = 1;
            while(keys.hasNext()){
                String next = new String(keys.next());
                log.info("No = {} , tableName = {}",Integer.toString(cnt), next);
                retJobj.put(Integer.toString(cnt), next);
                cnt++;
            }

            data = retJobj.toJSONString();

            if(data == null || data.equals("")){
                log.info("data is null");
                data = "Data is empty";
            }else{
                log.info("@@@@@@@Redis get Data = {}", data);
            }


            entity = new ResponseEntity<JSONObject>(retJobj, HttpStatus.OK);

        }catch(Exception e){
            e.printStackTrace();
            retJobj.put("message",e.getMessage());
            entity = new ResponseEntity<JSONObject>(retJobj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

}
