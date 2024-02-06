package com.boot.batchWork.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@Slf4j
public class HomeController {
    //@RequestMapping("/")
    public String home(){
        return "home";
    }

}
