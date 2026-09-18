package com.aec.ems.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.aec.ems.database.Database;
import com.aec.ems.model.Employee;

public class EmployeeRepositoryImpl
        implements EmployeeRepository {

    @Override
    public boolean addEmployee(
            Employee employee) {

        String sql =
                "INSERT INTO employee " +
                "(emp_name, email, salary, dept_id) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    employee.getEmpName()
            );

            ps.setString(
                    2,
                    employee.getEmail()
            );

            ps.setDouble(
                    3,
                    employee.getSalary()
            );

            ps.setInt(
                    4,
                    employee.getDeptId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );

            return false;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees =
                new ArrayList<>();

        String sql =
                "SELECT e.emp_id, e.emp_name, " +
                "e.email, e.salary, e.dept_id, " +
                "d.dept_name " +
                "FROM employee e " +
                "JOIN department d " +
                "ON e.dept_id = d.dept_id " +
                "ORDER BY e.emp_id";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            while (rs.next()) {

                Employee employee =
                        new Employee(
                                rs.getInt("emp_id"),
                                rs.getString("emp_name"),
                                rs.getString("email"),
                                rs.getDouble("salary"),
                                rs.getInt("dept_id"),
                                rs.getString("dept_name")
                        );

                employees.add(employee);
            }

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );
        }

        return employees;
    }

    @Override
    public Employee findEmployeeById(
            int empId) {

        String sql =
                "SELECT e.emp_id, e.emp_name, " +
                "e.email, e.salary, e.dept_id, " +
                "d.dept_name " +
                "FROM employee e " +
                "JOIN department d " +
                "ON e.dept_id = d.dept_id " +
                "WHERE e.emp_id = ?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );
        }

        return null;
    }

    @Override
    public List<Employee> searchEmployee(
            String keyword) {

        List<Employee> employees =
                new ArrayList<>();

        String sql =
                "SELECT e.emp_id, e.emp_name, " +
                "e.email, e.salary, e.dept_id, " +
                "d.dept_name " +
                "FROM employee e " +
                "JOIN department d " +
                "ON e.dept_id = d.dept_id " +
                "WHERE e.emp_name LIKE ? " +
                "OR e.email LIKE ? " +
                "OR d.dept_name LIKE ? " +
                "ORDER BY e.emp_id";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            String search =
                    "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                employees.add(
                        new Employee(
                                rs.getInt("emp_id"),
                                rs.getString("emp_name"),
                                rs.getString("email"),
                                rs.getDouble("salary"),
                                rs.getInt("dept_id"),
                                rs.getString("dept_name")
                        )
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );
        }

        return employees;
    }

    @Override
    public boolean updateEmployee(
            Employee employee) {

        String sql =
                "UPDATE employee " +
                "SET emp_name=?, email=?, " +
                "salary=?, dept_id=? " +
                "WHERE emp_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(
                    1,
                    employee.getEmpName()
            );

            ps.setString(
                    2,
                    employee.getEmail()
            );

            ps.setDouble(
                    3,
                    employee.getSalary()
            );

            ps.setInt(
                    4,
                    employee.getDeptId()
            );

            ps.setInt(
                    5,
                    employee.getEmpId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean deleteEmployee(
            int empId) {

        String sql =
                "DELETE FROM employee " +
                "WHERE emp_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Database Error: " +
                    e.getMessage()
            );

            return false;
        }
    }

    @Override
    public boolean departmentExists(
            int deptId) {

        String sql =
                "SELECT dept_id " +
                "FROM department " +
                "WHERE dept_id=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, deptId);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public boolean emailExists(
            String email) {

        String sql =
                "SELECT emp_id " +
                "FROM employee " +
                "WHERE email=?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, email);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public boolean emailExistsForOtherEmployee(
            String email,
            int empId) {

        String sql =
                "SELECT emp_id " +
                "FROM employee " +
                "WHERE email=? " +
                "AND emp_id<>?";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setInt(2, empId);

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            return false;
        }
    }

    @Override
    public void showSalaryReport() {

        String sql =
                "SELECT COUNT(*) AS total, " +
                "COALESCE(AVG(salary),0) AS average_salary, " +
                "COALESCE(MAX(salary),0) AS highest_salary, " +
                "COALESCE(MIN(salary),0) AS lowest_salary, " +
                "COALESCE(SUM(salary),0) AS total_salary " +
                "FROM employee";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                System.out.println();
                System.out.println(
                        "========================================"
                );
                System.out.println(
                        "              HR SALARY REPORT"
                );
                System.out.println(
                        "========================================"
                );

                System.out.println(
                        "Total Employees : " +
                        rs.getInt("total")
                );

                System.out.printf(
                        "Average Salary  : %.2f%n",
                        rs.getDouble("average_salary")
                );

                System.out.printf(
                        "Highest Salary  : %.2f%n",
                        rs.getDouble("highest_salary")
                );

                System.out.printf(
                        "Lowest Salary   : %.2f%n",
                        rs.getDouble("lowest_salary")
                );

                System.out.printf(
                        "Total Salary    : %.2f%n",
                        rs.getDouble("total_salary")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Report Error: " +
                    e.getMessage()
            );
        }
    }

    @Override
    public void showDepartmentReport() {

        String sql =
                "SELECT d.dept_name, " +
                "COUNT(e.emp_id) AS employee_count, " +
                "COALESCE(AVG(e.salary),0) AS avg_salary " +
                "FROM department d " +
                "LEFT JOIN employee e " +
                "ON d.dept_id=e.dept_id " +
                "GROUP BY d.dept_id, d.dept_name " +
                "ORDER BY employee_count DESC";

        try (Connection con =
                     Database.getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql);
             ResultSet rs =
                     ps.executeQuery()) {

            System.out.println();
            System.out.println(
                    "========================================"
            );
            System.out.println(
                    "          DEPARTMENT REPORT"
            );
            System.out.println(
                    "========================================"
            );

            System.out.printf(
                    "%-20s %-15s %-15s%n",
                    "Department",
                    "Employees",
                    "Avg Salary"
            );

            System.out.println(
                    "----------------------------------------"
            );

            while (rs.next()) {

                System.out.printf(
                        "%-20s %-15d %-15.2f%n",
                        rs.getString("dept_name"),
                        rs.getInt("employee_count"),
                        rs.getDouble("avg_salary")
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Report Error: " +
                    e.getMessage()
            );
        }
    }
}