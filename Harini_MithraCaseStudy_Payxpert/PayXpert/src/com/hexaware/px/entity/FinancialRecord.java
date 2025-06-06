package com.hexaware.px.entity;

import java.time.LocalDate;

public class FinancialRecord {
    private int recordID;
    private int employeeID;
    private LocalDate recordDate;
    private String description;
    private double amount;
    private String recordType;

    public FinancialRecord() {}

    public FinancialRecord(int recordID, int employeeID, LocalDate recordDate,
                           String description, double amount, String recordType) {
        this.recordID = recordID;
        this.employeeID = employeeID;
        this.recordDate = recordDate;
        this.description = description;
        this.amount = amount;
        this.recordType = recordType;
    }

    // Getters and Setters
    public int getRecordID() { return recordID; }
    public void setRecordID(int recordID) { this.recordID = recordID; }

    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public LocalDate getRecordDate() { return recordDate; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        this.amount = amount;
    }

    public String getRecordType() { return recordType; }
    public void setRecordType(String recordType) {
        if (recordType == null || recordType.trim().isEmpty()) {
            throw new IllegalArgumentException("Record type cannot be null or empty.");
        }
        this.recordType = recordType;
    }

    @Override
    public String toString() {
        return "FinancialRecord{" +
                "recordID=" + recordID +
                ", employeeID=" + employeeID +
                ", recordDate=" + recordDate +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", recordType='" + recordType + '\'' +
                '}';
    }
}
