package com.example.dummy.controller;

import com.example.dummy.responses.BaseResponce;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dummy")
public class Controller {



    @GetMapping
    public String getStatus(){
        return BaseResponce.responseStatus("Login1","ok");
    }
    @PostMapping
    public String postStatus(@RequestParam(value = "login") String login, @RequestParam(value = "password") String password){
        return BaseResponce.responseData(login,password);
    }
}
