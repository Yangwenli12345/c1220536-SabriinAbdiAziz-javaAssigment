package com.employee.emp.system.controller;

import com.employee.emp.system.model.Employee;
import com.employee.emp.system.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAll();
    }

    @PostMapping
    public String addEmployee(@RequestBody Employee employee) {
        int result = employeeRepository.insert(employee);
        return result == 1 ? "Employee added successfully." : "Failed to add employee.";
    }

    @PutMapping
    public String updateEmployee(@RequestBody Employee employee) {
        int result = employeeRepository.update(employee);
        return result == 1 ? "Employee updated successfully." : "Failed to update employee.";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        int result = employeeRepository.delete(id);
        return result == 1 ? "Employee deleted successfully." : "Failed to delete employee.";
    }
}
