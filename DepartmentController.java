package com.aec.ems.controller;

import java.util.List;

import com.aec.ems.model.Department;
import com.aec.ems.service.DepartmentService;

public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(
            DepartmentService service) {

        this.service = service;
    }

    public boolean addDepartment(
            Department department) {

        return service.addDepartment(
                department
        );
    }

    public List<Department>
    getAllDepartments() {

        return service.getAllDepartments();
    }

    public Department findDepartmentById(
            int deptId) {

        return service.findDepartmentById(
                deptId
        );
    }

    public boolean updateDepartment(
            Department department) {

        return service.updateDepartment(
                department
        );
    }

    public boolean deleteDepartment(
            int deptId) {

        return service.deleteDepartment(
                deptId
        );
    }
}