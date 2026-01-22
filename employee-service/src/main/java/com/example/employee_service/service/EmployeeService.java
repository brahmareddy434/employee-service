package com.example.employee_service.service;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.request.EmployeeRequest;
import com.example.employee_service.response.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public EmployeeResponse register(@Valid EmployeeRequest req) {
        Employee e = new Employee();
        e.setName(req.name);
        e.setEmail(req.email);
        e.setDepartment(req.department);

        Employee saved = repo.save(e);
        return new EmployeeResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getDepartment());
    }

    public EmployeeResponse getById(Long id) throws ChangeSetPersister.NotFoundException {
        Employee e = repo.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return new EmployeeResponse(e.getId(), e.getName(), e.getEmail(), e.getDepartment());
    }
}
