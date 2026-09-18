package com.aec.ems.model;

public class Department {

    private int deptId;
    private String deptName;

    public Department() {
    }

    public Department(
            int deptId,
            String deptName) {

        this.deptId = deptId;
        this.deptName = deptName;
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
                "%-10d %-25s",
                deptId,
                deptName
        );
    }
}
