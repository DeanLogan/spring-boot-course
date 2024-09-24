package com.example.cruddemo.rest;

import com.example.cruddemo.entity.Employee;
import com.example.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employee/{employeeId} - get employee by id through request param
    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found: "+employeeId);
        }
        return theEmployee;
    }

    // add mapping for POST /employees - save employee through JSON in the body of the request
    @PostMapping("/employees")
    public Employee saveEmployees(@RequestBody Employee theEmployee) {
        // just in case they pass an id in JSON, set id to 0
        // this is to force a save of new item instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee through JSON in the body of the request
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for DELETE /employee/{employeeId} - delete employee by id through request param
    @DeleteMapping("/employee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null) {
            throw new RuntimeException("Employee id not found: "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id: "+employeeId;
    }

}
