package org.msh.departmentservice.client;

import org.msh.departmentservice.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@FeignClient(name = "EMPLOYEE-SERVICE")
public interface EmployeeFeignClnt {

    @GetMapping("/employee/get/department/{id}")
    public List<Employee> findByDepartmentId(@PathVariable Long id);

}
