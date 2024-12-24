package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiException;
import com.example.project3.DtoIn.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CustomerService {
private final CustomerRepository customerRepository;
        private final AuthRepository authRepository;


    public void registerCustomer(CustomerDTO customerDTO) {
        User myUser=authRepository.findUserByUserName(customerDTO.getUserName());
        if (myUser != null) {
            throw new ApiException("User already exists");
        }

        User myUser1=new User();

        myUser1.setUserName(customerDTO.getUserName());
        myUser1.setPassword(new BCryptPasswordEncoder().encode(customerDTO.getPassword()));
        myUser1.setEmail(customerDTO.getEmail());
        myUser1.setName(customerDTO.getName());
        myUser1.setRole("CUSTOMER");

        Customer customer=new Customer();
        customer.setId(null);
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customer.setUser(myUser1);

        authRepository.save(myUser1);
        customerRepository.save(customer);}

}



