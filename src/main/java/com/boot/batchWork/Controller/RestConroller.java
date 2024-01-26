package com.boot.batchWork.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RestConroller {
    @PostMapping(value = "/test")
    public String getYamlInfo(){
        System.out.println("Rest 테스트용 호출");
        return "";
    }

    @PostMapping("/dbinfo")
    public String getDbInfo(){
        ResponseEntity<>
        return "OK";
    }
}
