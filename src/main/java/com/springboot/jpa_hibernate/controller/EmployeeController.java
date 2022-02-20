package com.springboot.jpa_hibernate.controller;

import com.springboot.jpa_hibernate.model.Employee;
import com.springboot.jpa_hibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/apie")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return this.employeeService.addEmployee(employee);
    }

    @GetMapping("/employee/all")
    public ResponseEntity<ArrayList<Employee>> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @DeleteMapping(path = "/employee/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "/employee/update/{id}")
    public ResponseEntity<Employee> updateEmploye(@PathVariable("id") Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @PostMapping(path = "/employee/addProject/{idE}/project/{idP}")
    public ResponseEntity<String> asignProjectToEmployee(@PathVariable("idE") Long idEmployee, @PathVariable("idP") Long idProject){
        return employeeService.asignProjectToEmployee(idEmployee, idProject);
    }
}
