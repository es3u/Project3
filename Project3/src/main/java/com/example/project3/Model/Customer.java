package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {

    @Id
    private Integer id;

    @Column(unique = true,nullable = false)
//    @Column(columnDefinition = "varchar(100)")
    private String phone;

    /// Relation

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    /// Relations

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")

    private Set<Account> accounts;

}
