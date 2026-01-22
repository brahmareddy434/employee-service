package com.example.employee_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequest {
    @NotBlank public String name;
    @NotBlank @Email public String email;
    @NotBlank public String department;

    public EmployeeRequest(String arun, String s, String it) {
    }

    public EmployeeRequest() {

    }
}