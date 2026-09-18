package com.aec.ems.model;

public class Employee {

    private int empId;
    private String empName;
    private String email;
    private double salary;
    private int deptId;
    private String deptName;

    public Employee() {
    }

    public Employee(
            int empId,
            String empName,
            String email,
            double salary,
            int deptId) {

        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.salary = salary;
        this.deptId = deptId;
    }

    public Employee(
            int empId,
            String empName,
            String email,
            double salary,
            int deptId,
            String deptName) {

        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.salary = salary;
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {

        return String.format(
                "%-8d %-20s %-30s %-12.2f %-15s",
                empId,
                empName,
                email,
                salary,
                deptName
        );
    }
}