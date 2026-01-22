package com.example.employee_service.response;


public class EmployeeResponse {
    public Long id;
    public String name;
    public String email;
    public String department;

    public EmployeeResponse(Long id, String name, String email, String department) {
        this.id = id; this.name = name; this.email = email; this.department = department;
    }
}