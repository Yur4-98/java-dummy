package com.example.dummy.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.management.ConstructorParameters;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class BaseResponce {

    //private static final String GetRequest = "{\"login\":\"Login1\",\"status\":\"ok\"}"; пример ответа по статусу

    public static String responseStatus(String login, String status) {

        String response = "\"{\n\"login\":\"" +
                login + "\",\n\"status\":\"" +
                status + "\"\n}";
        return response;
    }

    public static String responseData(String login, String password){

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String FormattedDateTime = dateTime.format(formatter);

        String response = "\"{\n\"login\":\"" +
                login + "\",\n\"password\":\"" +
                password +"\",\n\"date\":\"" +
                FormattedDateTime + "\"\n}";
        return response;
    }
}
