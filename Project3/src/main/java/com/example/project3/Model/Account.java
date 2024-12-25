package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor

@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(unique = true,nullable = false)
    private String accountNumber ;
    @Column(nullable = false)
    private Double balance ;
    @Column(nullable = false)
    private Boolean isActive = false;


    ///Relations

    @ManyToOne
    @JsonIgnore
    private Customer customer ;


}
