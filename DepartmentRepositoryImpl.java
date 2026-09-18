package com.aec.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aec.ems.database.Database;
import com.aec.ems.model.Department;

public class DepartmentRepositoryImpl
        implements DepartmentRepository {

    @Override
    public boolean addDepartment(
            Department department) {

        String sql =
                "INSERT INTO department(dept_name) " +
                "VALUES(?)";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    department.getDeptName()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );

            return false;
        }
    }

    @Override
    public List<Department>
    getAllDepartments() {

        List<Department> departments =
                new ArrayList<>();

        String sql =
                "SELECT dept_id, dept_name " +
                "FROM department " +
                "ORDER BY dept_id";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                departments.add(
                        new Department(
                                rs.getInt("dept_id"),
                                rs.getString("dept_name")
                        )
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }

        return departments;
    }

    @Override
    public Department findDepartmentById(
            int deptId) {

        String sql =
                "SELECT dept_id, dept_name " +
                "FROM department " +
                "WHERE dept_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }

        return null;
    }

    @Override
    public boolean updateDepartment(
            Department department) {

        String sql =
                "UPDATE department " +
                "SET dept_name=? " +
                "WHERE dept_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    department.getDeptName()
            );

            ps.setInt(
                    2,
                    department.getDeptId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean deleteDepartment(
            int deptId) {

        String sql =
                "DELETE FROM department " +
                "WHERE dept_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Cannot delete department. " +
                    "Employees may be using it."
            );

            return false;
        }
    }
}