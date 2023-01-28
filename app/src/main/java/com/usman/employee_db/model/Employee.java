package com.usman.employee_db.model;

import androidx.annotation.NonNull;

public class Employee{
    private int empID;
    private String empName;
    private String empPhoneNo;
    private int empSalary;

    public Employee(String name, String phone, int salary)
    {
        empName = name;
        empPhoneNo = phone;
        empSalary = salary;
    }

    public Employee(int id, String name, String phone, int salary)
    {
        empID = id;
        empName = name;
        empPhoneNo = phone;
        empSalary = salary;
    }

    public Employee()
    {
        // do nothing
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhoneNo() {
        return empPhoneNo;
    }

    public void setEmpPhoneNo(String empPhoneNo) {
        this.empPhoneNo = empPhoneNo;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

    public String toString() {
        return "\nName \t" + empName + "\nPhone \t" + empPhoneNo + "\nSalary\t\t" + empSalary + "\n";
    }
}
