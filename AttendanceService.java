package com.aec.ems.service;

import java.util.List;

import com.aec.ems.model.Attendance;

public interface AttendanceService {

    boolean addAttendance(
            Attendance attendance);

    List<Attendance> getAttendanceByEmployee(
            int empId);

    void showAttendanceSummary(
            int empId);
}