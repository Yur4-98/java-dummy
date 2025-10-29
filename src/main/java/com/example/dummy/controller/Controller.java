package com.example.dummy.controller;

import com.example.dummy.requests.dataRequest;
import com.example.dummy.responses.BaseResponse;
import com.example.dummy.utility.delay_emulator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/dummy")
@Validated
public class Controller {

    delay_emulator delay = new delay_emulator();

    @GetMapping
    public String getStatus(){
        delay.delay(1000); // задержка отклика
        return "{\n\"login\":\"Login1\",\n\"status\":\"ok\"\n}";
    }

    @PostMapping
    public ResponseEntity<String> postStatus(@Valid @RequestBody dataRequest request) {
        delay.delay(1000); // задержка отклика

        final BaseResponse response;

        return new ResponseEntity<>(BaseResponse.responseData(request.getlogin(),request.getpassword()), HttpStatus.OK) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) {
                String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        return ResponseEntity.badRequest().body("Validation Error: " + errorMessage);
    }

}
