package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.update(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        employeeService.deleteById(id);
    }
}
