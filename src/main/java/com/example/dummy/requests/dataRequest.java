package com.example.dummy.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class dataRequest {

    private String login;
    private String password;

    public dataRequest(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataRequest request = objectMapper.readValue(json, dataRequest.class);
            login = request.login;
            password = request.password;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }




    public String getlogin() {
        return login;
    }
    public String getpassword() {
        return password;
    }
}
