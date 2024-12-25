package com.example.project3.DTOin;

import com.example.project3.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmplyeeDTOIn {

     /// User Model
    @NotEmpty(message = "user name can not be null")
    @Size(min = 4 , max = 10)
    private String username ;


    @NotEmpty(message = "password can not be null")
    @Min(value = 6)
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long, contain one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password ;

    @NotEmpty(message = "name can not be null")
    @Size(min = 2 , max = 20)
    private String name ;

    @Email
    private String email ;

    /// Employee Model

    @NotEmpty(message = "position can not be null")
    private String position ;


    @NotNull(message = "salary can not be null")
    @Positive
    private Double salary ;


}
