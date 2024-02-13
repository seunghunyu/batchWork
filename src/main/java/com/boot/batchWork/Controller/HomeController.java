package com.boot.batchWork.Controller;

import com.boot.batchWork.data.etc.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    @RequestMapping("/home")
    public String home(){
        return "main";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        log.info("################LOGIN START");
        log.info(user.toString());
//        log.info("id = {}", id);
//        log.info("pw = {}", pw);
        log.info("userId = {}, password = {}", user.getUserId(), user.getPassword());
        return "home";
    }

    @RequestMapping(value = "/login2", method = RequestMethod.POST)
    public String login2(@RequestParam("userId") String userId, @RequestParam("password") String password){
        log.info("################LOGIN START");
//        log.info("id = {}", id);
//        log.info("pw = {}", pw);
        log.info("userId = {}, password = {}",userId, password);
        return "home";
    }

}
