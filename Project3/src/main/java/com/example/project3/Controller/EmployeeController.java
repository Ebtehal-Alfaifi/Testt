package com.example.project3.Controller;

import com.example.project3.ApiResponse.Api;
import com.example.project3.DtoIn.EmployeeDTO;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity registerEmployee(@RequestBody @Valid EmployeeDTO employeeDTOin){
        employeeService.registerEmployee(employeeDTOin);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("register success"));
}
}
