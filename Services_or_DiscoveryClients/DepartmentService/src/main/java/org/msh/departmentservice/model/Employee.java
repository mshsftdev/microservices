package org.msh.departmentservice.model;

//record will be by default final,
// also, by default properties will be created using constructor,
// also, by default there will be getters but no setters
public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
