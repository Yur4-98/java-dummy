package com.example.dummy.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
@Setter
public class BaseResponse {

    public static String responseData(String login, String password){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String FormattedDateTime = dateTime.format(formatter);

        String response = "{\n\"login\":\"" +
                login + "\",\n\"password\":\"" +
                password +"\",\n\"date\":\"" +
                FormattedDateTime + "\"\n}";
        return response;
    }
}
