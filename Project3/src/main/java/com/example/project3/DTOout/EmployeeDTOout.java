package com.example.project3.DTOout;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTOout {


    private String username ;


//    private String password ;


    private String name ;


    private String email ;

    /// Employee Model

    private String position ;



    private Double salary ;
}
