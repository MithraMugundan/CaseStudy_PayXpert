package com.hexaware.px.presentation;
import com.hexaware.px.entity.*;
import com.hexaware.px.exception.EmployeeNotFoundException;
import com.hexaware.px.exception.PayRollGenerationException;
import com.hexaware.px.exception.TaxNotFoundException;
import com.hexaware.px.service.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class ClientUI {
	static Scanner scanner;

	static {
		scanner = new Scanner(System.in);
	}

	public ClientUI() {
	}

    public static void main(String[] args) throws EmployeeNotFoundException, PayRollGenerationException, TaxNotFoundException {
        //Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        IEmployeeService employeeService = new EmployeeServiceImp();
        IPayRollService payrollService = new PayRollServiceImp();
        ITaxService taxService = new TaxServiceImp();
        IFinancialRecordService financialService = new FinancialRecordServiceImp();

        while (flag) {
            System.out.println("\n=== Payroll Management System ===");
            System.out.println("1. Manage Employees");
            System.out.println("2. Manage Payroll");
            System.out.println("3. Manage Tax");
            System.out.println("4. Manage Financial Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleEmployeeOperations(employeeService);
                    break;
                case 2:
                    handlePayrollOperations(payrollService);
                    break;
                case 3:
                    handleTaxOperations(taxService);
                    break;
                case 4:
                    handleFinancialRecordOperations(financialService);
                    break;
                case 5:
                    flag = false;
                    System.out.println("Thank you for using the Payroll Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        //scanner.close();
    }
    public static void handleEmployeeOperations(IEmployeeService employeeService) throws EmployeeNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean back = true;

        while (back) {
            System.out.println("\n--- Employee Operations ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Get Employee By ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter Date of Birth (yyyy-mm-dd): ");
                    LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter Position: ");
                    String position = scanner.nextLine();

                    System.out.print("Enter Joining Date (yyyy-mm-dd): ");
                    LocalDate joiningDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Termination Date (yyyy-mm-dd) or leave empty: ");
                    String terminationDateStr = scanner.nextLine();
                    LocalDate terminationDate = null;
                    if (!terminationDateStr.isEmpty()) {
                        terminationDate = LocalDate.parse(terminationDateStr);
                    }

                    // Create and add the employee
                    Employee newEmp = new Employee();
                    IEmployeeService.addEmployee(newEmp);
                    System.out.println("Employee added successfully!");
                    break;

                case 2:
                    // View All Employees
                    List<Employee> allEmployees = IEmployeeService.getAllEmployees();
                    
                    // Check if the list is null and initialize it to an empty list if needed
                    if (allEmployees == null) {
                        allEmployees = new ArrayList<>();
                    }

                    if (allEmployees.isEmpty()) {
                        System.out.println("No employees found.");
                    } else {
                        for (Employee emp : allEmployees) {
                            System.out.println(emp);
                        }
                    }
                    break;


                case 3:
                    // Get Employee by ID
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();
                    Employee emp = employeeService.getEmployeeById(empId);
                    if (emp != null) {
                        System.out.println(emp);
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;

                case 4:
                    // Update Employee
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Employee empToUpdate = employeeService.getEmployeeById(updateId);

                    if (empToUpdate != null) {
                        System.out.print("Enter Updated First Name (" + empToUpdate.getFirstName() + "): ");
                        empToUpdate.setFirstName(scanner.nextLine());

                        System.out.print("Enter Updated Last Name (" + empToUpdate.getLastName() + "): ");
                        empToUpdate.setLastName(scanner.nextLine());

                        System.out.print("Enter Updated Email (" + empToUpdate.getEmail() + "): ");
                        empToUpdate.setEmail(scanner.nextLine());

                        employeeService.updateEmployee(empToUpdate);
                        System.out.println("Employee updated successfully!");
                    } else {
                        System.out.println("Employee not found!");
                    }
                    break;

                case 5:
                    // Remove Employee
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    employeeService.removeEmployee(removeId);
                    System.out.println("Employee removed successfully!");
                    break;

                case 6:
                    back = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        // scanner.close();
    }

    public static void handlePayrollOperations(IPayRollService payrollService) throws PayRollGenerationException {
        Scanner scanner = new Scanner(System.in);
        boolean back = true;

        while (back) {
            System.out.println("\n--- Payroll Operations ---");
            System.out.println("1. Generate Payroll");
            System.out.println("2. View Payroll by ID");
            System.out.println("3. View Payrolls for an Employee");
            System.out.println("4. View Payrolls for a Period");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Generate Payroll
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Pay Period Start Date (yyyy-mm-dd): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Pay Period End Date (yyyy-mm-dd): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    payrollService.generatePayroll(empId, startDate, endDate);
                    System.out.println("Payroll generated successfully!");
                    break;

                case 2:
                    // View Payroll by ID
                    System.out.print("Enter Payroll ID: ");
                    int payrollId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    PayRoll payroll = payrollService.getPayrollById(payrollId);
                    if (payroll != null) {
                        System.out.println(payroll);
                    } else {
                        System.out.println("Payroll not found!");
                    }
                    break;

                case 3:
                    // View Payrolls for an Employee
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    List<PayRoll> payrollsForEmployee = payrollService.getPayrollsForEmployee(employeeId);
                    if (payrollsForEmployee.isEmpty()) {
                        System.out.println("No payrolls found for this employee.");
                    } else {
                        for (PayRoll p : payrollsForEmployee) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 4:
                    // View Payrolls for a Period
                    System.out.print("Enter Pay Period Start Date (yyyy-mm-dd): ");
                    LocalDate periodStartDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Enter Pay Period End Date (yyyy-mm-dd): ");
                    LocalDate periodEndDate = LocalDate.parse(scanner.nextLine());

                    List<PayRoll> payrollsForPeriod = payrollService.getPayrollsForPeriod(periodStartDate, periodEndDate);
                    if (payrollsForPeriod.isEmpty()) {
                        System.out.println("No payrolls found for the given period.");
                    } else {
                        for (PayRoll p : payrollsForPeriod) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 5:
                    back = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        //scanner.close();
    }
    public static void handleTaxOperations(ITaxService taxService) throws TaxNotFoundException {
        Scanner scanner = new Scanner(System.in);
        boolean back = true;

        while (back) {
            System.out.println("\n--- Tax Operations ---");
            System.out.println("1. Calculate Tax");
            System.out.println("2. View Tax by ID");
            System.out.println("3. View Taxes for an Employee");
            System.out.println("4. View Taxes for a Year");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Calculate Tax
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Tax Year: ");
                    int taxYear = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Tax calculatedTax = taxService.calculateTax(empId, taxYear);
                    System.out.println("Calculated Tax: " + calculatedTax);
                    break;

                case 2:
                    // View Tax by ID
                    System.out.print("Enter Tax ID: ");
                    int taxId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    Tax tax = taxService.getTaxById(taxId);
                    if (tax != null) {
                        System.out.println(tax);
                    } else {
                        System.out.println("Tax record not found!");
                    }
                    break;

                case 3:
                    // View Taxes for an Employee
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    List<Tax> taxesForEmployee = taxService.getTaxesForEmployee(employeeId);
                    if (taxesForEmployee.isEmpty()) {
                        System.out.println("No tax records found for this employee.");
                    } else {
                        for (Tax t : taxesForEmployee) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 4:
                    // View Taxes for a Year
                    System.out.print("Enter Tax Year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    List<Tax> taxesForYear = taxService.getTaxesForYear(year);
                    if (taxesForYear.isEmpty()) {
                        System.out.println("No tax records found for this year.");
                    } else {
                        for (Tax t : taxesForYear) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 5:
                    back = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        //scanner.close();
    }

    public static void handleFinancialRecordOperations(IFinancialRecordService financialService) {
        Scanner scanner = new Scanner(System.in);
        boolean back = true;

        while (back) {
            System.out.println("\n--- Financial Record Operations ---");
            System.out.println("1. Add Financial Record");
            System.out.println("2. View Financial Record by ID");
            System.out.println("3. View Financial Records for an Employee");
            System.out.println("4. View Financial Records for a Date");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Financial Record
                    System.out.print("Enter Employee ID: ");
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    System.out.print("Enter Record Type (e.g., 'Salary', 'Bonus'): ");
                    String recordType = scanner.nextLine();

                    financialService.addFinancialRecord(employeeId, description, amount, recordType);
                    System.out.println("Financial Record Added Successfully!");
                    break;

                case 2:
                    // View Financial Record by ID
                    System.out.print("Enter Financial Record ID: ");
                    int recordId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    FinancialRecord financialRecord = financialService.getFinancialRecordById(recordId);
                    if (financialRecord != null) {
                        System.out.println(financialRecord);
                    } else {
                        System.out.println("Financial record not found!");
                    }
                    break;

                case 3:
                    // View Financial Records for an Employee
                    System.out.print("Enter Employee ID: ");
                    int empId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    List<FinancialRecord> recordsForEmployee = financialService.getFinancialRecordsForEmployee(empId);
                    if (recordsForEmployee.isEmpty()) {
                        System.out.println("No financial records found for this employee.");
                    } else {
                        for (FinancialRecord fr : recordsForEmployee) {
                            System.out.println(fr);
                        }
                    }
                    break;

                case 4:
                    // View Financial Records for a Date
                    System.out.print("Enter Record Date (YYYY-MM-DD): ");
                    String recordDateStr = scanner.nextLine();

                    // Convert the date string to Date object (using SimpleDateFormat or any other method)
                    // Assuming the date format is "YYYY-MM-DD"
                    Date recordDate = Date.valueOf(recordDateStr);

                    List<FinancialRecord> recordsForDate = financialService.getFinancialRecordsForDate(recordDate);
                    if (recordsForDate.isEmpty()) {
                        System.out.println("No financial records found for this date.");
                    } else {
                        for (FinancialRecord fr : recordsForDate) {
                            System.out.println(fr);
                        }
                    }
                    break;

                case 5:
                    back = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        //scanner.close();
    }

}

            

    
