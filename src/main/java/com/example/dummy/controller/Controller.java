package com.example.dummy.controller;

import com.example.dummy.responses.BaseResponse;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/dummy")
public class Controller {



    @GetMapping
    public String getStatus() throws InterruptedException {
        Thread.sleep(1000);
        return BaseResponse.responseStatus("Login1","ok");
    }
    @PostMapping
    public String postStatus(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password) throws InterruptedException {
        Thread.sleep(1000);
        return BaseResponse.responseData(login,password);
    }
}
