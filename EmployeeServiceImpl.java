package com.aec.ems.service;

import java.util.List;

import com.aec.ems.exception.EmployeeNotFoundException;
import com.aec.ems.model.Employee;
import com.aec.ems.repository.EmployeeRepository;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addEmployee(Employee employee) {

        if (employee.getEmpName() == null ||
                employee.getEmpName().trim().isEmpty()) {

            System.out.println("Employee name cannot be empty.");
            return false;
        }

        if (employee.getEmail() == null ||
                employee.getEmail().trim().isEmpty()) {

            System.out.println("Email cannot be empty.");
            return false;
        }

        if (!employee.getEmail().contains("@")) {

            System.out.println("Invalid email address.");
            return false;
        }

        if (employee.getSalary() <= 0) {

            System.out.println("Salary must be greater than 0.");
            return false;
        }

        if (!repository.departmentExists(employee.getDeptId())) {

            System.out.println(
                    "Department ID " + employee.getDeptId()
                            + " does not exist."
            );

            return false;
        }

        if (repository.emailExists(employee.getEmail())) {

            System.out.println("Email already exists.");
            return false;
        }

        return repository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {

        return repository.getAllEmployees();
    }

    @Override
    public Employee findEmployeeById(int empId) {

        Employee employee =
                repository.findEmployeeById(empId);

        if (employee == null) {

            throw new EmployeeNotFoundException(empId);
        }

        return employee;
    }

    @Override
    public List<Employee> searchEmployee(String keyword) {

        return repository.searchEmployee(keyword);
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        // Check ID first
        Employee existing =
                repository.findEmployeeById(employee.getEmpId());

        if (existing == null) {

            throw new EmployeeNotFoundException(
                    employee.getEmpId()
            );
        }

        if (employee.getEmpName() == null ||
                employee.getEmpName().trim().isEmpty()) {

            System.out.println("Employee name cannot be empty.");
            return false;
        }

        if (employee.getEmail() == null ||
                !employee.getEmail().contains("@")) {

            System.out.println("Invalid email address.");
            return false;
        }

        if (employee.getSalary() <= 0) {

            System.out.println("Salary must be greater than 0.");
            return false;
        }

        if (!repository.departmentExists(employee.getDeptId())) {

            System.out.println(
                    "Department ID " +
                    employee.getDeptId() +
                    " does not exist."
            );

            return false;
        }

        if (repository.emailExistsForOtherEmployee(
                employee.getEmail(),
                employee.getEmpId())) {

            System.out.println(
                    "Email already belongs to another employee."
            );

            return false;
        }

        return repository.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployee(int empId) {

        // Check ID first
        Employee existing =
                repository.findEmployeeById(empId);

        if (existing == null) {

            throw new EmployeeNotFoundException(empId);
        }

        return repository.deleteEmployee(empId);
    }

    @Override
    public void showSalaryReport() {

        repository.showSalaryReport();
    }

    @Override
    public void showDepartmentReport() {

        repository.showDepartmentReport();
    }
}