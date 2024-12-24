package com.example.project3.DtoIn;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    @NotEmpty(message = "user name can not be null")
    @Size(min = 3,max = 6,message = "user name can not be less than 3 ir more than 6")
    @Column(columnDefinition = "varchar(6) not null unique")
    private String userName;


    @Column(columnDefinition = "varchar(200) not null")
    @NotEmpty(message = "password can not be null")
    private String password;


    @NotEmpty(message = "name can not be null ")
    @Size(min = 2, max = 20, message = "name can not be less than 2 or more than 20")
    private String name ;

    @Email(message = "you should enter Valid email ")
    @Size(max = 20, message = "email character can not be more tha 20 character")
    private String email;



    @NotEmpty(message = "Position cannot be null")
    private String position;

    @NotNull(message = "salary can not be null")
    private Double salary;

}
