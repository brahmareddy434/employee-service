package com.example.employee_service.controller;

import com.example.employee_service.request.EmployeeRequest;
import com.example.employee_service.response.EmployeeResponse;
import com.example.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse register(@Valid @RequestBody EmployeeRequest req) {
        return employeeService.register(req);
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return employeeService.getById(id);
    }
}
