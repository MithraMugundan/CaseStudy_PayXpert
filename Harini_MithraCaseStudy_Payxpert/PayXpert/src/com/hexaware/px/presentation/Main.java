 package com.hexaware.px.presentation;
 import com.hexaware.px.dao.TaxDaoImpl;
import com.hexaware.px.dao.EmployeeDaoImpl;
import com.hexaware.px.entity.Employee;
import com.hexaware.px.entity.Tax;
import com.hexaware.px.exception.EmployeeNotFoundException;
import com.hexaware.px.exception.PayrollNotFoundException;
import com.hexaware.px.exception.TaxNotFoundException;
import com.hexaware.px.entity.Payroll;
import com.hexaware.px.entity.FinancialRecord;
import com.hexaware.px.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        IEmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
        ITaxService taxService = new TaxServiceImpl(new TaxDaoImpl());

        IPayrollService payrollService = new PayrollServiceImpl();
        IFinancialRecordService financialRecordService = new FinancialRecordServiceImpl();

        while (true) {
            System.out.println("------ Management System ------");
            System.out.println("1. Employee Management");
            System.out.println("2. Tax Management");
            System.out.println("3. Payroll Management");
            System.out.println("4. Financial Record Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("----- Employee Management -----");
                    System.out.println("1. Add Employee");
                    System.out.println("2. Get Employee by ID");
                    System.out.println("3. Update Employee");
                    System.out.println("4. Delete Employee");
                    System.out.println("5. List All Employees");
                    System.out.print("Enter your choice: ");
                    int empChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (empChoice) {
                        case 1:
                            System.out.println("Enter employee details:");
                            System.out.print("Employee ID: ");
                            int empId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("First Name: ");
                            String firstName = scanner.nextLine();

                            System.out.print("Last Name: ");
                            String lastName = scanner.nextLine();

                            System.out.print("Date of Birth (yyyy-mm-dd): ");
                            String dob = scanner.nextLine();
                            LocalDate dateOfBirth = LocalDate.parse(dob);

                            System.out.print("Gender: ");
                            String gender = scanner.nextLine();

                            System.out.print("Email: ");
                            String email = scanner.nextLine();

                            System.out.print("Phone Number: ");
                            String phoneNumber = scanner.nextLine();

                            System.out.print("Address: ");
                            String address = scanner.nextLine();

                            System.out.print("Position: ");
                            String position = scanner.nextLine();

                            System.out.print("Joining Date (yyyy-mm-dd): ");
                            String joiningDateStr = scanner.nextLine();
                            LocalDate joiningDate = LocalDate.parse(joiningDateStr);

                            System.out.print("Termination Date (yyyy-mm-dd) [Leave blank if not applicable]: ");
                            String terminationDateStr = scanner.nextLine();
                            LocalDate terminationDate = terminationDateStr.isEmpty() ? null : LocalDate.parse(terminationDateStr);

                            Employee employee = new Employee(empId, firstName, lastName, dateOfBirth, gender, email, phoneNumber, address, position, joiningDate, terminationDate);
                            employeeService.addEmployee(employee);
                            System.out.println("Employee added successfully.");
                            break;

                        case 2:
                            System.out.print("Enter Employee ID: ");
                            int searchId = scanner.nextInt();
                            Employee emp = null;
                            try {
                                emp = employeeService.getEmployeeById(searchId);
                            } catch (EmployeeNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (emp != null) {
                                System.out.println("Employee Details: " + emp);
                            } else {
                                System.out.println("Employee not found.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter Employee ID to update: ");
                            int updateId = scanner.nextInt();
                            scanner.nextLine();

                            Employee empToUpdate = null;
                            try {
                                empToUpdate = employeeService.getEmployeeById(updateId);
                            } catch (EmployeeNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (empToUpdate != null) {
                                System.out.println("Enter new details (Leave blank to keep current):");
                                
                                System.out.print("First Name (" + empToUpdate.getFirstName() + "): ");
                                String newFirstName = scanner.nextLine();
                                if (!newFirstName.isEmpty()) {
                                    empToUpdate.setFirstName(newFirstName);
                                }

                                System.out.print("Last Name (" + empToUpdate.getLastName() + "): ");
                                String newLastName = scanner.nextLine();
                                if (!newLastName.isEmpty()) {
                                    empToUpdate.setLastName(newLastName);
                                }

                                System.out.print("Email (" + empToUpdate.getEmail() + "): ");
                                String newEmail = scanner.nextLine();
                                if (!newEmail.isEmpty()) {
                                    empToUpdate.setEmail(newEmail);
                                }

                                System.out.print("Position (" + empToUpdate.getPosition() + "): ");
                                String newPosition = scanner.nextLine();
                                if (!newPosition.isEmpty()) {
                                    empToUpdate.setPosition(newPosition);
                                }

                                try {
                                    employeeService.updateEmployee(empToUpdate);
                                } catch (EmployeeNotFoundException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("Employee updated successfully.");
                            } else {
                                System.out.println("Employee not found.");
                            }
                            break;

                        case 4:
                            System.out.print("Enter Employee ID to delete: ");
                            int deleteId = scanner.nextInt();
                            try {
                                employeeService.deleteEmployee(deleteId);
                            } catch (EmployeeNotFoundException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Employee deleted successfully.");
                            break;

                        case 5:
                            System.out.println("Employee List:");
                            List<Employee> employees = employeeService.getAllEmployees();
                            for (Employee empList : employees) {
                                System.out.println(empList);
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("----- Tax Management -----");
                    System.out.println("1. Get Tax by ID");
                    System.out.println("2. List All Taxes");
                    System.out.print("Enter your choice: ");
                    int taxChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (taxChoice) {
                        case 1:
                            System.out.print("Enter Tax ID: ");
                            int searchTaxId = scanner.nextInt();
                            Tax foundTax = null;
                            try {
                                foundTax = taxService.getTaxById(searchTaxId);
                            } catch (TaxNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (foundTax != null) {
                                System.out.println("Tax Details: " + foundTax);
                            } else {
                                System.out.println("Tax not found.");
                            }
                            break;

                        case 2:
                            List<Tax> taxList = taxService.getAllTaxes();
                            System.out.println("Tax List:");
                            if (taxList.isEmpty()) {
                                System.out.println("No taxes found.");
                            } else {
                                for (Tax tax : taxList) {
                                    System.out.println(tax);
                                }
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("----- Payroll Management -----");
                    System.out.println("1. Add Payroll");
                    System.out.println("2. Get Payroll by ID");
                    System.out.println("3. Delete Payroll");
                    System.out.print("Enter your choice: ");
                    int payrollChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (payrollChoice) {
                        case 1:
                            System.out.println("Enter payroll details:");
                            System.out.print("Payroll ID: ");
                            int payrollId = scanner.nextInt();

                            System.out.print("Employee ID: ");
                            int employeePayrollId = scanner.nextInt();

                            System.out.print("Base Salary: ");
                            double baseSalary = scanner.nextDouble();

                            System.out.print("Bonus: ");
                            double bonus = scanner.nextDouble();

                            System.out.print("Deductions: ");
                            double deductions = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("Payroll Date (yyyy-mm-dd): ");
                            String payrollDateStr = scanner.nextLine();
                            LocalDate payrollDate = LocalDate.parse(payrollDateStr);

                            Payroll payroll = new Payroll();
                            payrollService.addPayroll(payroll);
                            System.out.println("Payroll added successfully.");
                            break;

                        case 2:
                            System.out.print("Enter Payroll ID: ");
                            int searchPayrollId = scanner.nextInt();
                            Payroll foundPayroll = null;
                            try {
                                foundPayroll = payrollService.getPayrollById(searchPayrollId);
                            } catch (PayrollNotFoundException e) {
                                e.printStackTrace();
                            }
                            if (foundPayroll != null) {
                                System.out.println("Payroll Details: " + foundPayroll);
                            } else {
                                System.out.println("Payroll not found.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter Payroll ID to delete: ");
                            int deletePayrollId = scanner.nextInt();
                            try {
                                payrollService.deletePayroll(deletePayrollId);
                            } catch (PayrollNotFoundException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Payroll deleted successfully.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("----- Financial Record Management -----");
                    System.out.println("1. Add Financial Record");
                    System.out.println("2. Get Financial Record by ID");
                    System.out.println("3. Update Financial Record");
                    System.out.println("4. Delete Financial Record");
                    System.out.print("Enter your choice: ");
                    int financialRecordChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (financialRecordChoice) {
                        case 1:
                            System.out.println("Enter financial record details:");
                            System.out.print("Amount: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();

                            FinancialRecord financialRecord = new FinancialRecord();
                            financialRecord.setAmount(amount);
                            financialRecordService.addFinancialRecord(financialRecord);
                            System.out.println("Financial Record added successfully.");
                            break;

                        case 2:
                            System.out.print("Enter Financial Record ID: ");
                            int searchRecordId = scanner.nextInt();
                            FinancialRecord foundRecord = financialRecordService.getFinancialRecordById(searchRecordId);
                            if (foundRecord != null) {
                                System.out.println("Financial Record Details: " + foundRecord);
                            } else {
                                System.out.println("Financial Record not found.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter Financial Record ID to update: ");
                            int updateRecordId = scanner.nextInt();
                            scanner.nextLine();

                            FinancialRecord recordToUpdate = financialRecordService.getFinancialRecordById(updateRecordId);
                            if (recordToUpdate != null) {
                                System.out.print("New Amount: ");
                                double newAmount = scanner.nextDouble();
                                recordToUpdate.setAmount(newAmount);

                                financialRecordService.updateFinancialRecord(recordToUpdate);
                                System.out.println("Financial Record updated successfully.");
                            } else {
                                System.out.println("Financial Record not found.");
                            }
                            break;

                        case 4:
                            System.out.print("Enter Financial Record ID to delete: ");
                            int deleteRecordId = scanner.nextInt();
                            financialRecordService.deleteFinancialRecord(deleteRecordId);
                            System.out.println("Financial Record deleted successfully.");
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
