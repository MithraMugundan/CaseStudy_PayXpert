drop database payxpert;
create database payxpert;
use  payxpert;
CREATE TABLE employee (
    employeeId INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    dateOfBirth DATE,
    gender VARCHAR(10),
    email VARCHAR(100),
    phoneNumber VARCHAR(15),
    address VARCHAR(255),
    position VARCHAR(50),
    joiningDate DATE,
    terminationDate DATE
);CREATE TABLE financial_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    employeeId INT,
    record_date DATE,
    description VARCHAR(255),
    amount DOUBLE,
    record_type VARCHAR(50),
    FOREIGN KEY (employeeId) REFERENCES employee(employeeId)
);

CREATE TABLE payroll (
    payrollID INT PRIMARY KEY auto_increment,
    employeeID INT,
    payPeriodStartDate DATE,
    payPeriodEndDate DATE,
    basicSalary DOUBLE,
    overtimePay DOUBLE,
    deductions DOUBLE,
    netSalary DOUBLE,
    FOREIGN KEY (employeeID) REFERENCES employee(employeeId)
);
CREATE TABLE taxes (
    tax_id INT PRIMARY KEY,
    employee_id INT,                  
    tax_year INT,                   
    taxable_income DOUBLE,            
    tax_amount DOUBLE,                
    FOREIGN KEY (employee_id) REFERENCES employee(employeeId)  -- foreign key to reference employee table
);

INSERT INTO employee VALUES
(1, 'John', 'Doe', '1990-05-15', 'Male', 'john.doe@example.com', '9876543210', 'New York', 'Manager', '2015-06-01', NULL),
(2, 'Jane', 'Smith', '1988-09-20', 'Female', 'jane.smith@example.com', '8765432109', 'Chicago', 'HR', '2016-08-15', NULL),
(3, 'Alice', 'Johnson', '1992-03-10', 'Female', 'alice.j@example.com', '7654321098', 'Los Angeles', 'Analyst', '2017-01-10', NULL),
(4, 'Bob', 'Brown', '1985-11-30', 'Male', 'bob.brown@example.com', '6543210987', 'Houston', 'Developer', '2018-04-05', NULL),
(5, 'Eve', 'Davis', '1995-01-25', 'Female', 'eve.davis@example.com', '5432109876', 'Phoenix', 'Designer', '2019-07-20', NULL),
(6, 'Mike', 'Wilson', '1983-07-14', 'Male', 'mike.w@example.com', '4321098765', 'Seattle', 'Engineer', '2013-09-18', NULL),
(7, 'Sara', 'Taylor', '1991-02-28', 'Female', 'sara.t@example.com', '3210987654', 'Boston', 'QA', '2020-11-11', NULL),
(8, 'Tom', 'Anderson', '1989-12-12', 'Male', 'tom.a@example.com', '2109876543', 'Denver', 'DevOps', '2014-05-09', NULL),
(9, 'Lily', 'Martinez', '1994-06-18', 'Female', 'lily.m@example.com', '1098765432', 'Austin', 'Support', '2021-01-01', NULL),
(10, 'Chris', 'Lee', '1990-10-05', 'Male', 'chris.lee@example.com', '9988776655', 'San Francisco', 'Lead', '2012-03-25', NULL);
INSERT INTO financial_records VALUES
(101, 1, '2024-01-01', 'Travel Reimbursement', 150.00, 'Credit'),
(102, 2, '2024-01-05', 'Client Meeting Expense', 200.00, 'Debit'),
(103, 3, '2024-02-10', 'Laptop Replacement', 1200.00, 'Debit'),
(104, 4, '2024-02-15', 'Referral Bonus', 500.00, 'Credit'),
(105, 5, '2024-03-01', 'Team Dinner', 250.00, 'Debit'),
(106, 6, '2024-03-10', 'Remote Setup Reimbursement', 300.00, 'Credit'),
(107, 7, '2024-03-20', 'Training Expense', 450.00, 'Debit'),
(108, 8, '2024-04-01', 'Software License Refund', 100.00, 'Credit'),
(109, 9, '2024-04-10', 'Wellness Reimbursement', 200.00, 'Credit'),
(110, 10, '2024-04-15', 'Lost Equipment Charge', 400.00, 'Debit');
INSERT INTO payroll VALUES
(201, 1, '2024-03-01', '2024-03-31', 6000.00, 300.00, 200.00, 6100.00),
(202, 2, '2024-03-01', '2024-03-31', 5500.00, 250.00, 150.00, 5600.00),
(203, 3, '2024-03-01', '2024-03-31', 5000.00, 200.00, 100.00, 5100.00),
(204, 4, '2024-03-01', '2024-03-31', 6500.00, 300.00, 250.00, 6550.00),
(205, 5, '2024-03-01', '2024-03-31', 4800.00, 100.00, 100.00, 4800.00),
(206, 6, '2024-03-01', '2024-03-31', 7000.00, 400.00, 300.00, 7100.00),
(207, 7, '2024-03-01', '2024-03-31', 5200.00, 100.00, 80.00, 5220.00),
(208, 8, '2024-03-01', '2024-03-31', 5900.00, 150.00, 100.00, 5950.00),
(209, 9, '2024-03-01', '2024-03-31', 5100.00, 120.00, 90.00, 5130.00),
(210, 10, '2024-03-01', '2024-03-31', 6700.00, 350.00, 250.00, 6800.00);
INSERT INTO taxes VALUES
(301, 1, 2023, 72000.00, 10800.00),
(302, 2, 2023, 66000.00, 9900.00),
(303, 3, 2023, 60000.00, 9000.00),
(304, 4, 2023, 78000.00, 11700.00),
(305, 5, 2023, 57600.00, 8640.00),
(306, 6, 2023, 84000.00, 12600.00),
(307, 7, 2023, 62400.00, 9360.00),
(308, 8, 2023, 70800.00, 10620.00),
(309, 9, 2023, 61200.00, 9180.00),
(310, 10, 2023, 80400.00, 12060.00);



