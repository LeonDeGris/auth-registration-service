package com.bmstu.flowrence.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterCredentialsDto implements RequestDto  {

    String firstName;
    String lastName;
    String email;
    String password;

}
