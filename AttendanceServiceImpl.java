package com.aec.ems.service;

import java.util.List;

import com.aec.ems.model.Attendance;
import com.aec.ems.repository.AttendanceRepository;

public class AttendanceServiceImpl
        implements AttendanceService {

    private AttendanceRepository repository;

    public AttendanceServiceImpl(
            AttendanceRepository repository) {

        this.repository = repository;
    }

    @Override
    public boolean addAttendance(
            Attendance attendance) {

        String status =
                attendance.getStatus();

        if (!status.equalsIgnoreCase("Present") &&
            !status.equalsIgnoreCase("Absent") &&
            !status.equalsIgnoreCase("Leave")) {

            System.out.println(
                    "Invalid status!"
            );

            System.out.println(
                    "Use Present, Absent or Leave."
            );

            return false;
        }

        attendance.setStatus(
                capitalize(status)
        );

        return repository.addAttendance(
                attendance
        );
    }

    private String capitalize(
            String value) {

        return value.substring(0, 1)
                .toUpperCase()
                + value.substring(1)
                        .toLowerCase();
    }

    @Override
    public List<Attendance>
    getAttendanceByEmployee(
            int empId) {

        return repository
                .getAttendanceByEmployee(empId);
    }

    @Override
    public void showAttendanceSummary(
            int empId) {

        repository.showAttendanceSummary(
                empId
        );
    }
}