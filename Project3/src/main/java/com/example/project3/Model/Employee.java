package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    private Integer id ;

    @Column(nullable = false)
    private String position ;

    @Column(nullable = false)

    private Double salary ;

    /// Relation

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user ;

}
