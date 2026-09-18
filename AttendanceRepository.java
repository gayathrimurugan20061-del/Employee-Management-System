package com.aec.ems.repository;

import java.util.List;

import com.aec.ems.model.Attendance;

public interface AttendanceRepository {

    boolean addAttendance(
            Attendance attendance);

    List<Attendance> getAttendanceByEmployee(
            int empId);

    void showAttendanceSummary(
            int empId);
}