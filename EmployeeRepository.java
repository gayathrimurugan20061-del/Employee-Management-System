package com.aec.ems.repository;

import java.util.List;

import com.aec.ems.model.Employee;

public interface EmployeeRepository {

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee findEmployeeById(int empId);

    List<Employee> searchEmployee(
            String keyword);

    boolean updateEmployee(
            Employee employee);

    boolean deleteEmployee(int empId);

    boolean departmentExists(int deptId);

    boolean emailExists(String email);

    boolean emailExistsForOtherEmployee(
            String email,
            int empId);

    void showSalaryReport();

    void showDepartmentReport();
}