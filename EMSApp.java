package com.aec.ems.main;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.aec.ems.controller.AttendanceController;
import com.aec.ems.controller.DepartmentController;
import com.aec.ems.controller.EmployeeController;
import com.aec.ems.exception.EmployeeNotFoundException;
import com.aec.ems.model.Attendance;
import com.aec.ems.model.Department;
import com.aec.ems.model.Employee;
import com.aec.ems.repository.AttendanceRepositoryImpl;
import com.aec.ems.repository.DepartmentRepositoryImpl;
import com.aec.ems.repository.EmployeeRepositoryImpl;
import com.aec.ems.service.AttendanceServiceImpl;
import com.aec.ems.service.DepartmentServiceImpl;
import com.aec.ems.service.EmployeeServiceImpl;

public class EMSApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==============================
        // REPOSITORY OBJECTS
        // ==============================

        EmployeeRepositoryImpl employeeRepository =
                new EmployeeRepositoryImpl();

        DepartmentRepositoryImpl departmentRepository =
                new DepartmentRepositoryImpl();

        AttendanceRepositoryImpl attendanceRepository =
                new AttendanceRepositoryImpl();

        // ==============================
        // SERVICE OBJECTS
        // ==============================

        EmployeeServiceImpl employeeService =
                new EmployeeServiceImpl(employeeRepository);

        DepartmentServiceImpl departmentService =
                new DepartmentServiceImpl(departmentRepository);

        AttendanceServiceImpl attendanceService =
                new AttendanceServiceImpl(attendanceRepository);

        // ==============================
        // CONTROLLER OBJECTS
        // ==============================

        EmployeeController employeeController =
                new EmployeeController(employeeService);

        DepartmentController departmentController =
                new DepartmentController(departmentService);

        AttendanceController attendanceController =
                new AttendanceController(attendanceService);

        // ==============================
        // MAIN MENU
        // ==============================

        while (true) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       EMPLOYEE HR MANAGEMENT SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Add Department");
            System.out.println("7. View Departments");
            System.out.println("8. Update Department");
            System.out.println("9. Delete Department");
            System.out.println("10. Mark Attendance");
            System.out.println("11. View Attendance");
            System.out.println("12. Attendance Summary");
            System.out.println("13. HR Salary Report");
            System.out.println("14. Department Report");
            System.out.println("15. Exit");
            System.out.println("==========================================");

            int choice = readInt(sc, "Enter choice: ");

            switch (choice) {

                case 1:
                    addEmployee(sc, employeeController);
                    break;

                case 2:
                    viewEmployees(employeeController);
                    break;

                case 3:
                    searchEmployee(sc, employeeController);
                    break;

                case 4:
                    updateEmployee(sc, employeeController);
                    break;

                case 5:
                    deleteEmployee(sc, employeeController);
                    break;

                case 6:
                    addDepartment(sc, departmentController);
                    break;

                case 7:
                    viewDepartments(departmentController);
                    break;

                case 8:
                    updateDepartment(sc, departmentController);
                    break;

                case 9:
                    deleteDepartment(sc, departmentController);
                    break;

                case 10:
                    markAttendance(
                            sc,
                            employeeController,
                            attendanceController
                    );
                    break;

                case 11:
                    viewAttendance(
                            sc,
                            employeeController,
                            attendanceController
                    );
                    break;

                case 12:
                    attendanceSummary(
                            sc,
                            employeeController,
                            attendanceController
                    );
                    break;

                case 13:
                    employeeController.showSalaryReport();
                    break;

                case 14:
                    employeeController.showDepartmentReport();
                    break;

                case 15:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // =========================================
    // ADD EMPLOYEE
    // =========================================

    private static void addEmployee(
            Scanner sc,
            EmployeeController controller) {

        System.out.println();
        System.out.println("---------- ADD EMPLOYEE ----------");

        String name =
                readString(sc, "Enter employee name: ");

        String email =
                readString(sc, "Enter email: ");

        double salary =
                readDouble(sc, "Enter salary: ");

        int deptId =
                readInt(sc, "Enter department ID: ");

        Employee employee =
                new Employee(
                        0,
                        name,
                        email,
                        salary,
                        deptId
                );

        if (controller.addEmployee(employee)) {

            System.out.println(
                    "Employee added successfully."
            );

        } else {

            System.out.println(
                    "Employee could not be added."
            );
        }
    }

    // =========================================
    // VIEW EMPLOYEES
    // =========================================

    private static void viewEmployees(
            EmployeeController controller) {

        List<Employee> employees =
                controller.getAllEmployees();

        System.out.println();
        System.out.println("---------- EMPLOYEE LIST ----------");

        if (employees.isEmpty()) {

            System.out.println("No employees found.");
            return;
        }

        System.out.printf(
                "%-8s %-20s %-30s %-12s %-15s%n",
                "ID",
                "Name",
                "Email",
                "Salary",
                "Department"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Employee employee : employees) {

            System.out.println(employee);
        }
    }

    // =========================================
    // SEARCH
    // =========================================

    private static void searchEmployee(
            Scanner sc,
            EmployeeController controller) {

        String keyword =
                readString(
                        sc,
                        "Enter name/email/department: "
                );

        List<Employee> employees =
                controller.searchEmployee(keyword);

        if (employees.isEmpty()) {

            System.out.println("No employee found.");
            return;
        }

        for (Employee employee : employees) {

            System.out.println(employee);
        }
    }

    // =========================================
    // UPDATE EMPLOYEE
    // =========================================

    private static void updateEmployee(
            Scanner sc,
            EmployeeController controller) {

        System.out.println();
        System.out.println("---------- UPDATE EMPLOYEE ----------");

        int empId =
                readInt(sc, "Enter employee ID: ");

        try {

            // IMPORTANT:
            // Check ID BEFORE asking other details
            Employee existing =
                    controller.findEmployeeById(empId);

            System.out.println(
                    "Employee found: " +
                    existing.getEmpName()
            );

            String name =
                    readString(sc, "Enter new name: ");

            String email =
                    readString(sc, "Enter new email: ");

            double salary =
                    readDouble(sc, "Enter new salary: ");

            int deptId =
                    readInt(sc, "Enter new department ID: ");

            Employee employee =
                    new Employee(
                            empId,
                            name,
                            email,
                            salary,
                            deptId
                    );

            if (controller.updateEmployee(employee)) {

                System.out.println(
                        "Employee updated successfully."
                );

            } else {

                System.out.println(
                        "Employee could not be updated."
                );
            }

        } catch (EmployeeNotFoundException e) {

            System.out.println(e.getMessage());

            System.out.println(
                    "Update cancelled."
            );
        }
    }

    // =========================================
    // DELETE EMPLOYEE
    // =========================================

    private static void deleteEmployee(
            Scanner sc,
            EmployeeController controller) {

        System.out.println();
        System.out.println("---------- DELETE EMPLOYEE ----------");

        int empId =
                readInt(sc, "Enter employee ID: ");

        try {

            Employee employee =
                    controller.findEmployeeById(empId);

            System.out.println(
                    "Employee: " +
                    employee.getEmpName()
            );

            String confirm =
                    readString(
                            sc,
                            "Delete this employee? (yes/no): "
                    );

            if (!confirm.equalsIgnoreCase("yes")) {

                System.out.println("Delete cancelled.");
                return;
            }

            if (controller.deleteEmployee(empId)) {

                System.out.println(
                        "Employee deleted successfully."
                );

            } else {

                System.out.println(
                        "Employee could not be deleted."
                );
            }

        } catch (EmployeeNotFoundException e) {

            System.out.println(e.getMessage());

            System.out.println(
                    "Delete cancelled."
            );
        }
    }

    // =========================================
    // ADD DEPARTMENT
    // =========================================

    private static void addDepartment(
            Scanner sc,
            DepartmentController controller) {

        String name =
                readString(
                        sc,
                        "Enter department name: "
                );

        Department department =
                new Department(0, name);

        if (controller.addDepartment(department)) {

            System.out.println(
                    "Department added successfully."
            );

        } else {

            System.out.println(
                    "Department could not be added."
            );
        }
    }

    // =========================================
    // VIEW DEPARTMENTS
    // =========================================

    private static void viewDepartments(
            DepartmentController controller) {

        List<Department> departments =
                controller.getAllDepartments();

        System.out.println();
        System.out.println("---------- DEPARTMENTS ----------");

        for (Department department : departments) {

            System.out.println(department);
        }
    }

    // =========================================
    // UPDATE DEPARTMENT
    // =========================================

    private static void updateDepartment(
            Scanner sc,
            DepartmentController controller) {

        int deptId =
                readInt(sc, "Enter department ID: ");

        Department existing =
                controller.findDepartmentById(deptId);

        if (existing == null) {

            System.out.println(
                    "Department ID " +
                    deptId +
                    " not found."
            );

            System.out.println(
                    "Update cancelled."
            );

            return;
        }

        String newName =
                readString(
                        sc,
                        "Enter new department name: "
                );

        Department department =
                new Department(
                        deptId,
                        newName
                );

        if (controller.updateDepartment(department)) {

            System.out.println(
                    "Department updated successfully."
            );

        } else {

            System.out.println(
                    "Department could not be updated."
            );
        }
    }

    // =========================================
    // DELETE DEPARTMENT
    // =========================================

    private static void deleteDepartment(
            Scanner sc,
            DepartmentController controller) {

        int deptId =
                readInt(sc, "Enter department ID: ");

        Department existing =
                controller.findDepartmentById(deptId);

        if (existing == null) {

            System.out.println(
                    "Department ID " +
                    deptId +
                    " not found."
            );

            System.out.println(
                    "Delete cancelled."
            );

            return;
        }

        String confirm =
                readString(
                        sc,
                        "Delete department? (yes/no): "
                );

        if (!confirm.equalsIgnoreCase("yes")) {

            System.out.println("Delete cancelled.");
            return;
        }

        if (controller.deleteDepartment(deptId)) {

            System.out.println(
                    "Department deleted successfully."
            );

        } else {

            System.out.println(
                    "Department cannot be deleted."
            );
        }
    }

    // =========================================
    // MARK ATTENDANCE
    // =========================================

    private static void markAttendance(
            Scanner sc,
            EmployeeController employeeController,
            AttendanceController attendanceController) {

        int empId =
                readInt(sc, "Enter employee ID: ");

        try {

            Employee employee =
                    employeeController
                            .findEmployeeById(empId);

            System.out.println(
                    "Employee: " +
                    employee.getEmpName()
            );

            String status =
                    readString(
                            sc,
                            "Status (Present/Absent/Leave): "
                    );

            Attendance attendance =
                    new Attendance(
                            0,
                            empId,
                            Date.valueOf(LocalDate.now()),
                            status
                    );

            if (attendanceController
                    .addAttendance(attendance)) {

                System.out.println(
                        "Attendance marked successfully."
                );

            } else {

                System.out.println(
                        "Attendance could not be marked."
                );
            }

        } catch (EmployeeNotFoundException e) {

            System.out.println(e.getMessage());

            System.out.println(
                    "Cannot mark attendance."
            );
        }
    }

    // =========================================
    // VIEW ATTENDANCE
    // =========================================

    private static void viewAttendance(
            Scanner sc,
            EmployeeController employeeController,
            AttendanceController attendanceController) {

        int empId =
                readInt(sc, "Enter employee ID: ");

        try {

            Employee employee =
                    employeeController
                            .findEmployeeById(empId);

            System.out.println(
                    "Employee: " +
                    employee.getEmpName()
            );

            List<Attendance> list =
                    attendanceController
                            .getAttendanceByEmployee(empId);

            if (list.isEmpty()) {

                System.out.println(
                        "No attendance records."
                );

                return;
            }

            for (Attendance attendance : list) {

                System.out.println(
                        attendance.getAttendanceDate()
                        + " - "
                        + attendance.getStatus()
                );
            }

        } catch (EmployeeNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // =========================================
    // ATTENDANCE SUMMARY
    // =========================================

    private static void attendanceSummary(
            Scanner sc,
            EmployeeController employeeController,
            AttendanceController attendanceController) {

        int empId =
                readInt(sc, "Enter employee ID: ");

        try {

            employeeController.findEmployeeById(empId);

            attendanceController
                    .showAttendanceSummary(empId);

        } catch (EmployeeNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // =========================================
    // INPUT METHODS
    // =========================================

    private static int readInt(
            Scanner sc,
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        sc.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a valid integer."
                );
            }
        }
    }

    private static double readDouble(
            Scanner sc,
            String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        sc.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Enter a valid number."
                );
            }
        }
    }

    private static String readString(
            Scanner sc,
            String message) {

        System.out.print(message);

        return sc.nextLine().trim();
    }
}
