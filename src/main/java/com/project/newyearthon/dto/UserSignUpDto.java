package com.project.newyearthon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    private String email;
    private String password;
    private String phoneNumber;
    private boolean part;
}
