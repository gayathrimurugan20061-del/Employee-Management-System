package com.aec.ems.exception;

public class EmployeeNotFoundException
        extends RuntimeException {

    public EmployeeNotFoundException(int empId) {

        super(
                "Employee ID " +
                empId +
                " not found!"
        );
    }
}