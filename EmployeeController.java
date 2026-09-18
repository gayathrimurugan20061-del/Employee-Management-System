package com.aec.ems.controller;

import java.util.List;

import com.aec.ems.model.Employee;
import com.aec.ems.service.EmployeeService;

public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(
            EmployeeService service) {

        this.service = service;
    }

    public boolean addEmployee(
            Employee employee) {

        return service.addEmployee(employee);
    }

    public List<Employee> getAllEmployees() {

        return service.getAllEmployees();
    }

    public Employee findEmployeeById(
            int empId) {

        return service.findEmployeeById(empId);
    }

    public List<Employee> searchEmployee(
            String keyword) {

        return service.searchEmployee(keyword);
    }

    public boolean updateEmployee(
            Employee employee) {

        return service.updateEmployee(
                employee
        );
    }

    public boolean deleteEmployee(
            int empId) {

        return service.deleteEmployee(empId);
    }

    public void showSalaryReport() {

        service.showSalaryReport();
    }

    public void showDepartmentReport() {

        service.showDepartmentReport();
    }
}