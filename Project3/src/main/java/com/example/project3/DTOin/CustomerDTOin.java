package com.example.project3.DTOin;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOin {

    @NotEmpty(message = "username is empty")
    @Size(min = 4, max = 10, message = "username must be between 4 and 10")
    private String username;

    @NotEmpty(message = "password is empty")
    @Size(min = 6, max = 20, message = "password must be between 6 and 20")
    private String password;

    @NotEmpty(message = "name is empty")
    @Size(min = 2, max = 20, message = "varchar(20) not null")
    private String name;

    @Email(message = "Please enter a valid email format")
    private String email;

    @Pattern(regexp = "^(\\+966|0)?5\\d{8}$",   message = "Phone number must start with +966 or 05 and be followed by 8 digits")
    private String phone;

}
