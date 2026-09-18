package com.aec.ems.model;

import java.sql.Date;

public class Attendance {

    private int attendanceId;
    private int empId;
    private Date attendanceDate;
    private String status;

    public Attendance() {
    }

    public Attendance(
            int attendanceId,
            int empId,
            Date attendanceDate,
            String status) {

        this.attendanceId = attendanceId;
        this.empId = empId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

