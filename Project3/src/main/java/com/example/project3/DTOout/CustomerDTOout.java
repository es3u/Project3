package com.example.project3.DTOout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTOout {

    private String username ;

//    private String password ;

    private String name ;

    private String email ;

    private String phone;

    private Set<AccountDTOout> accounts;

}
