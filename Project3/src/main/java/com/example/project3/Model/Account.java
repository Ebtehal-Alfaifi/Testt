package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "account number can not be null")
    @Pattern(
            regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$",
            message = "Card number must follow the format 'XXXX-XXXX-XXXX-XXXX', where X is a digit."
    )
    @Column(columnDefinition = "int not null")
    private Integer accountNumber;

    @NotNull(message = "balance can not be null")
//    @DecimalMin(value = "0.0", inclusive = true, message = "Balance must be a non-negative decimal number") // accept zero or more
    @Column(columnDefinition = "double not null")
    private Double balance;

    @AssertFalse
    @Column(columnDefinition = "boolean not null")
    private Boolean isActive;


    @ManyToOne
    @JsonIgnore
    private Customer customer;

}
