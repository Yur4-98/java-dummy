package com.example.dummy.controller;

import com.example.dummy.requests.dataRequest;
import com.example.dummy.responses.BaseResponse;
import com.example.dummy.utility.delay_emulator;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/dummy")
public class Controller {

    delay_emulator delay = new delay_emulator();

    @GetMapping
    public String getStatus(){
        delay.delay(1000); // задержка отклика
        return "{\n\"login\":\"Login1\",\n\"status\":\"ok\"\n}";
    }
    @PostMapping
    public String postStatus(@RequestBody String jsonrequest) throws InterruptedException {
        delay.delay(1000); // задержка отклика

        final BaseResponse response;

        dataRequest request = new dataRequest(jsonrequest);


        String userlogin = request.getlogin();

        String password = request.getpassword();

        return BaseResponse.responseData(userlogin,password);
    }
}
