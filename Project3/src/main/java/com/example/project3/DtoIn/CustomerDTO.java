package com.example.project3.DtoIn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    @NotEmpty(message = "user name can not be null")
    @Size(min = 3,max = 6,message = "user name can not be less than 3 ir more than 6")
    private String userName;


    @NotEmpty(message = "password can not be null")
    // ما استخدمت باترن لانه ما يضبط مع السكيورتي
    private String password;


    @NotEmpty(message = "name can not be null")
    @Size(min = 2, max = 20, message = "name can not be less than 2 or more than 20")
    private String name;

    @Email(message = "you should enter Valid email ")
    @Size(max = 20, message = "email character can not be more tha 20 character")
    private String email;


    @NotEmpty(message = "phone number can not be null")
    @Pattern(regexp = "^05[0-9]{8}$", message = "Phone number must start with '05' and have 10 digits")
    private String phoneNumber;
}


