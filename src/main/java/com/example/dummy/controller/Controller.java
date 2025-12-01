package com.example.dummy.controller;


import com.example.dummy.model.DataBaseWorker;
import com.example.dummy.model.FileWorker;
import com.example.dummy.model.User;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


@RestController
@RequestMapping("/dummy")
@Validated
public class Controller {




    @Value("${global.delay}")
    int nanos;

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private DataBaseWorker dataBaseWorker;

    public void delay(){
        try {
            Thread.sleep(nanos);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping
    public ResponseEntity<User> getUser(@RequestBody User login){
        delay(); // задержка отклика

        User user = dataBaseWorker.getUserByLogin(login.getLogin());

        FileWorker<User> fileWorker = new FileWorker<>();
        fileWorker.write("got.txt",user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-type","application/json")
                .body(user);
    }

    @PostMapping
    public ResponseEntity<User> postStatus(@Valid @RequestBody User user) {
        delay(); // задержка отклика



        dataBaseWorker.postUser(user);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-type","application/json")
                .body(user);
    }

    @GetMapping("/file")
    public ResponseEntity<ArrayList<User>> getUserFromFile(){
        delay(); // задержка отклика

        ArrayList<User> arr = new ArrayList<>();
        FileWorker<User> fileWorker = new FileWorker<>();
        arr = fileWorker.readUsersFromFile();



        return ResponseEntity
                .status(HttpStatus.OK)
                .header("Content-type","application/json")
                .body(arr);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException exception) {
                String errorMessage = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        return ResponseEntity.badRequest().body("Validation Error: " + errorMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public void RuntimeException(RuntimeException exception) {
        LOGGER.error("Произошла ошибка: {}", exception.getMessage(), exception);
    }

    @ExceptionHandler(DataBaseWorker.UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(DataBaseWorker.UserNotFoundException exception) {
        String errorMessage = exception.getMessage();
        return ResponseEntity.internalServerError().body(errorMessage);
    }
}
