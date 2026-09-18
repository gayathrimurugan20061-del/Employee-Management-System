package com.aec.ems.service;

import java.util.List;

import com.aec.ems.model.Department;
import com.aec.ems.repository.DepartmentRepository;

public class DepartmentServiceImpl
        implements DepartmentService {

    private DepartmentRepository repository;

    public DepartmentServiceImpl(
            DepartmentRepository repository) {

        this.repository = repository;
    }

    @Override
    public boolean addDepartment(
            Department department) {

        if (department.getDeptName() == null ||
            department.getDeptName()
                    .trim()
                    .isEmpty()) {

            System.out.println(
                    "Department name cannot be empty."
            );

            return false;
        }

        return repository.addDepartment(
                department
        );
    }

    @Override
    public List<Department>
    getAllDepartments() {

        return repository.getAllDepartments();
    }

    @Override
    public Department findDepartmentById(
            int deptId) {

        return repository.findDepartmentById(
                deptId
        );
    }

    @Override
    public boolean updateDepartment(
            Department department) {

        Department existing =
                repository.findDepartmentById(
                        department.getDeptId()
                );

        if (existing == null) {

            System.out.println(
                    "Department ID " +
                    department.getDeptId() +
                    " not found!"
            );

            return false;
        }

        return repository.updateDepartment(
                department
        );
    }

    @Override
    public boolean deleteDepartment(
            int deptId) {

        Department existing =
                repository.findDepartmentById(
                        deptId
                );

        if (existing == null) {

            System.out.println(
                    "Department ID " +
                    deptId +
                    " not found!"
            );

            return false;
        }

        return repository.deleteDepartment(
                deptId
        );
    }
}