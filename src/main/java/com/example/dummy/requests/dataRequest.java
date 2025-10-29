package com.example.dummy.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class dataRequest {



    @NotEmpty(message = "login не должен быть пустым")
    private String login;



    @NotEmpty(message = "password не должен быть пустым")
    private String password;

    public String getlogin() {
        return login;
    }
    public String getpassword() {
        return password;
    }
}
