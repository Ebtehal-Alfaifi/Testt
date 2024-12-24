package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiException;
import com.example.project3.DtoIn.EmployeeDTO;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class EmployeeService {

        private final EmployeeRepository employeeRepository;
        private final AuthRepository authRepository;

        public List<Employee> getAllEmployee(){
            return employeeRepository.findAll();
        }

        public  void registerEmployee(EmployeeDTO employeeDTO){
            User myUser=authRepository.findUserByUserName(employeeDTO.getUserName());
            if (myUser != null) {
                throw new ApiException("User already exists");
            }

            User myUser1=new User();

            myUser1.setUserName(employeeDTO.getUserName());
            myUser1.setPassword(new BCryptPasswordEncoder().encode(employeeDTO.getPassword()));
            myUser1.setEmail(employeeDTO.getEmail());
            myUser1.setName(employeeDTO.getName());
            myUser1.setRole("EMPLOYEE");
            authRepository.save(myUser1);

            Employee employee=new Employee();
            employee.setId(null);
            employee.setPosition(employeeDTO.getPosition());
            employee.setSalary(employeeDTO.getSalary());

            employee.setUser(myUser1);


            employeeRepository.save(employee);
        }
        
    }
