package com.example.dummy.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotEmpty(message = "login не должен быть пустым")
    private String login;
    @NotEmpty(message = "password не должен быть пустым")
    private String password;
    @NotEmpty(message = "email не должен быть пустым")
    private String email;
    private Timestamp date;


    @Override
    public String toString(){
        return "User{" +
                "login="+ login + '\n' +
                ", password=" + password + '\n' +
                ", email=" + email + '\n' +
                ", date=" + date +
                "}";
    }

    public void setCurrentDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = Timestamp.valueOf(dateTime.format(formatter));
    }

    public Timestamp getDate() {
        setCurrentDate();
        return date;
    }
}
