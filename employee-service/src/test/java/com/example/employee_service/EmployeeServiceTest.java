package com.example.employee_service;

import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.request.EmployeeRequest;
import com.example.employee_service.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    @Test
    void register_savesEmployee() {
        EmployeeRepository repo = Mockito.mock(EmployeeRepository.class);

        Employee saved = new Employee();
        saved.setId(1L);
        saved.setName("Arun");
        saved.setEmail("arun@test.com");
        saved.setDepartment("IT");

        Mockito.when(repo.save(Mockito.any(Employee.class))).thenReturn(saved);

        EmployeeService service = new EmployeeService(repo);

        EmployeeRequest req = new EmployeeRequest();
        req.name = "Arun";
        req.email = "arun@test.com";
        req.department = "IT";

        var res = service.register(req);
        assertEquals(1L, res.id);
        assertEquals("Arun", res.name);
    }
}