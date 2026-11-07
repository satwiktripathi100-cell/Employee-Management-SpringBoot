package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // Employee list
    List<Employee> employees = new ArrayList<>();

    // (B) Add Employee - POST
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {

        employees.add(emp);

        return ResponseEntity.status(HttpStatus.CREATED).body(emp);
    }

    // (C) Get All Employees - GET
    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }

    // Get Employee By ID - GET
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {

        for (Employee emp : employees) {

            if (emp.getId() == id) {
                return ResponseEntity.ok(emp);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // (D) Update Employee - PUT
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmp) {

        for (Employee emp : employees) {

            if (emp.getId() == id) {

                emp.setName(updatedEmp.getName());
                emp.setDepartment(updatedEmp.getDepartment());
                emp.setSalary(updatedEmp.getSalary());

                return ResponseEntity.ok(emp);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // (E) Delete Employee - DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {

        for (Employee emp : employees) {

            if (emp.getId() == id) {

                employees.remove(emp);

                return ResponseEntity.ok("Employee deleted successfully");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Employee not found");
    }
}