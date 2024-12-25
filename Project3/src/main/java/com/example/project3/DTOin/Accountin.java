package com.example.project3.DTOin;

import com.example.project3.Model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accountin {

    @NotEmpty(message = "account Number can not be null ")
    @Pattern(regexp = "^(^\\d{4}-\\d{4}-\\d{4}-\\d{4}$)$")
    private String accountNumber ;

    @NotNull(message = "balance can not be null")
    @Positive
    private Double balance ;

    @NotNull
    private Boolean isActive = false;

}
