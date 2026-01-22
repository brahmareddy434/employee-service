package com.example.employee_service;

import com.example.employee_service.controller.EmployeeController;
import com.example.employee_service.request.EmployeeRequest;
import com.example.employee_service.response.EmployeeResponse;
import com.example.employee_service.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired MockMvc mockMvc;

    @MockitoBean
    EmployeeService service;

    @Test
    void getById_returnsEmployee() throws Exception {
        Mockito.when(service.getById(1L))
                .thenReturn(new EmployeeResponse(1L, "Arun", "arun@test.com", "IT"));

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Arun"));
    }

    @Test
    void register_returns201() throws Exception {
        EmployeeRequest employeeRequest = new EmployeeRequest("Arun", "aruntest.com", "IT");
        Mockito.when(service.register(employeeRequest))
                .thenReturn(new EmployeeResponse(any(), "Arun", "arun@test.com", "IT"));

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
          {"name":"Arun","email":"arun@test.com","department":"IT"}
        """))
                .andExpect(status().isCreated());
    }
}