package com.aec.ems.service;

import java.util.List;

import com.aec.ems.model.Employee;

public interface EmployeeService {

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee findEmployeeById(int empId);

    List<Employee> searchEmployee(
            String keyword);

    boolean updateEmployee(
            Employee employee);

    boolean deleteEmployee(int empId);

    void showSalaryReport();

    void showDepartmentReport();
}