package org.msh.departmentservice.client;

import org.msh.departmentservice.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClnt {

    @GetExchange("/employee/get/department/{id}")
    public List<Employee> findByDepartmentId(@PathVariable Long id);

}
