package org.msh.employeeservice.repository;

import org.msh.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepo {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll(){
        return employees;
    }

    public Employee addEmployee(Employee d)
    {
        employees.add(d);
        return d;
    }

    public Employee findById(Long id)
    {
        return employees
                .stream()
                .filter(e -> e.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> findByDepartmentId(Long departmentId)
    {
        return employees
                .stream()
                .filter(e -> e.departmentId().equals(departmentId))
                .toList();
    }
}
