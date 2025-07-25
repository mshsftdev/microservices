package org.msh.departmentservice.controller;

import org.msh.departmentservice.client.EmployeeClnt;
import org.msh.departmentservice.client.EmployeeFeignClnt;
import org.msh.departmentservice.model.Department;
import org.msh.departmentservice.repository.DepartmentRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.logging.Logger;
import org.slf4j.Logger;

@RestController
@RequestMapping("/department")
public class DepartmentCtrl {

    private final DepartmentRepo departmentRepo;
    private final EmployeeClnt employeeClnt;
    private final EmployeeFeignClnt employeeFeignClnt;
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(DepartmentCtrl.class);

    @Autowired
    public DepartmentCtrl(DepartmentRepo departmentRepo, EmployeeClnt employeeClnt, EmployeeFeignClnt employeeFeignClnt) {
        this.departmentRepo = departmentRepo;
        this.employeeClnt = employeeClnt;
        this.employeeFeignClnt = employeeFeignClnt;
    }

    @PostMapping("/add")
    public Department add(@RequestBody Department d)
    {
        LOGGER.info(String.format("Department add: {}", d.toString()));
        return departmentRepo.addDepartment(d);
    }

    @GetMapping("/get/all")
    public List<Department> findAll()
    {
        LOGGER.info("Department findAll");
        return departmentRepo.findAll();
    }

    @GetMapping("/get/{id}")
    public Department findById(@PathVariable Long id)
    {
        LOGGER.info(String.format("Department findById: {}", id));
        return departmentRepo.findById(id);
    }




    @GetMapping("/get/allWithEmployees")
    public List<Department> findAllWithEmployees()
    {
        LOGGER.info("Department findAllWithEmployees");
        List<Department> lst = departmentRepo.findAll();
        lst.forEach(d -> d.setEmployees(
                employeeClnt.findByDepartmentId(d.getId())
        ));
        return  lst;
    }

    @GetMapping("/get/allWithEmployeesUsingFeignClient")
    public List<Department> findAllWithEmployeesUsingFeignClient()
    {
        LOGGER.info("Department findAllWithEmployees");
        List<Department> lst = departmentRepo.findAll();
        lst.forEach(d -> d.setEmployees(
                employeeFeignClnt.findByDepartmentId(d.getId())
        ));
        return  lst;
    }


}
