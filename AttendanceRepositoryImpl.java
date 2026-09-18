package com.aec.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aec.ems.database.Database;
import com.aec.ems.model.Attendance;

public class AttendanceRepositoryImpl
        implements AttendanceRepository {

    @Override
    public boolean addAttendance(
            Attendance attendance) {

        String sql =
                "INSERT INTO attendance " +
                "(emp_id, attendance_date, status) " +
                "VALUES (?, ?, ?)";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(
                    1,
                    attendance.getEmpId()
            );

            ps.setDate(
                    2,
                    attendance.getAttendanceDate()
            );

            ps.setString(
                    3,
                    attendance.getStatus()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            if (e.getMessage()
                    .contains("Duplicate")) {

                System.out.println(
                        "Attendance already marked " +
                        "for this employee today."
                );

            } else {

                System.out.println(
                        "Attendance Error: " +
                        e.getMessage()
                );
            }

            return false;
        }
    }

    @Override
    public List<Attendance>
    getAttendanceByEmployee(
            int empId) {

        List<Attendance> list =
                new ArrayList<>();

        String sql =
                "SELECT attendance_id, emp_id, " +
                "attendance_date, status " +
                "FROM attendance " +
                "WHERE emp_id=? " +
                "ORDER BY attendance_date DESC";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                list.add(
                        new Attendance(
                                rs.getInt(
                                        "attendance_id"
                                ),
                                rs.getInt(
                                        "emp_id"
                                ),
                                rs.getDate(
                                        "attendance_date"
                                ),
                                rs.getString(
                                        "status"
                                )
                        )
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }

        return list;
    }

    @Override
    public void showAttendanceSummary(
            int empId) {

        String sql =
                "SELECT " +
                "COUNT(*) AS total_days, " +
                "SUM(status='Present') AS present_days, " +
                "SUM(status='Absent') AS absent_days, " +
                "SUM(status='Leave') AS leave_days " +
                "FROM attendance " +
                "WHERE emp_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                int total =
                        rs.getInt("total_days");

                int present =
                        rs.getInt("present_days");

                int absent =
                        rs.getInt("absent_days");

                int leave =
                        rs.getInt("leave_days");

                double percentage = 0;

                if (total > 0) {

                    percentage =
                            (present * 100.0)
                            / total;
                }

                System.out.println();

                System.out.println(
                        "========================================"
                );

                System.out.println(
                        "          ATTENDANCE SUMMARY"
                );

                System.out.println(
                        "========================================"
                );

                System.out.println(
                        "Total Days  : " + total
                );

                System.out.println(
                        "Present     : " + present
                );

                System.out.println(
                        "Absent      : " + absent
                );

                System.out.println(
                        "Leave       : " + leave
                );

                System.out.printf(
                        "Attendance %%: %.2f%%%n",
                        percentage
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }
}