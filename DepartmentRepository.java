package com.aec.ems.repository;

import java.util.List;

import com.aec.ems.model.Department;

public interface DepartmentRepository {

    boolean addDepartment(
            Department department);

    List<Department> getAllDepartments();

    Department findDepartmentById(
            int deptId);

    boolean updateDepartment(
            Department department);

    boolean deleteDepartment(
            int deptId);
}
