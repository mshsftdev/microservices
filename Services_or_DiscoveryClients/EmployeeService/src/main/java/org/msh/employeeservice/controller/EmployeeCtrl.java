package org.msh.employeeservice.controller;

import org.msh.employeeservice.model.Employee;
import org.msh.employeeservice.repository.EmployeeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeCtrl {

    private final EmployeeRepo employeeRepo;
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(EmployeeCtrl.class);


    @Autowired
    public EmployeeCtrl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @PostMapping("/add")
    public Employee add(@RequestBody Employee e)
    {
        LOGGER.info(String.format("Employee add: {}", e.toString()));
        return employeeRepo.addEmployee(e);
    }

    @GetMapping("/get/all")
    public List<Employee> findAll()
    {
        LOGGER.info("Employee findAll");
        return employeeRepo.findAll();
    }

    @GetMapping("/get/{id}")
    public Employee findById(@PathVariable Long id)
    {
        LOGGER.info(String.format("Employee findById: {}", id));
        return employeeRepo.findById(id);
    }

    @GetMapping("/get/department/{id}")
    public List<Employee> findByDepartmentId(@PathVariable Long id)
    {
        LOGGER.info(String.format("Employee findById: {}", id));
        return employeeRepo.findByDepartmentId(id);
    }
}
