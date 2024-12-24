package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String position;

//    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative decimal number")// accept zero or more
    @Column(columnDefinition = "double not null")
    private Double salary;


    @OneToOne
    @MapsId
    private User user;
}
