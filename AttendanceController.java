package com.aec.ems.controller;

import java.util.List;

import com.aec.ems.model.Attendance;
import com.aec.ems.service.AttendanceService;

public class AttendanceController {

    private AttendanceService service;

    public AttendanceController(
            AttendanceService service) {

        this.service = service;
    }

    public boolean addAttendance(
            Attendance attendance) {

        return service.addAttendance(
                attendance
        );
    }

    public List<Attendance>
    getAttendanceByEmployee(
            int empId) {

        return service
                .getAttendanceByEmployee(empId);
    }

    public void showAttendanceSummary(
            int empId) {

        service.showAttendanceSummary(empId);
    }
}