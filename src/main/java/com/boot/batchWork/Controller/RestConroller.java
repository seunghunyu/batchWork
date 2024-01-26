package com.boot.batchWork.Controller;

import com.boot.batchWork.util.DataBases;
import com.boot.batchWork.util.GetYmlProperty;
import com.boot.batchWork.util.TestConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class RestConroller {
    @Autowired
    private final GetYmlProperty getYmlProperty;

    @Autowired
    private final TestConfig testConfig;


    @PostMapping(value = "/test")
    public String getYamlInfo(){
        System.out.println("Rest 테스트용 호출");
        return "";
    }

    @PostMapping("/dbinfo")
    public ResponseEntity<String> getDbInfo(){
        ResponseEntity<String> entity = null;
//        database:
//            brch1:
//              name: CRMDB
//            brch2:
//              name: REBMDB
//            brch3:
//              name: DSDMT
//        GetYmlProperty getYmlProperty = new GetYmlProperty();

        try{
            String[] brch = getYmlProperty.getBrch();
            for(int i = 0 ; i < brch.length ; i++){
                log.info(brch[i]);
            }

            entity = new ResponseEntity<String>("OK", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/dbinfo2")
    public ResponseEntity<String> getDbInfo2(@Value("${demo.api}") String demoApi){

        ResponseEntity<String> entity = null;
        try{
            log.info(demoApi);
            entity = new ResponseEntity<String>("OK", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/dbinfo3")
    public ResponseEntity<String> getDbInfo3(){
        ResponseEntity<String> entity = null;
        JSONObject jobj = new JSONObject();
        jobj.put("db","obzmeta");
        jobj.put("user","ecube");
        jobj.put("password","ecube");
        try{

            entity = new ResponseEntity<String>(jobj.toString(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
