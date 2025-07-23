package org.msh.departmentservice.repository;

import org.msh.departmentservice.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartmentRepo {

    private List<Department> departments = new ArrayList<>();

    public List<Department> findAll(){
        return departments;
    }

    public Department addDepartment(Department d)
    {
        departments.add(d);
        return d;
    }

    public Department findById(Long id)
    {
        return departments
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}
