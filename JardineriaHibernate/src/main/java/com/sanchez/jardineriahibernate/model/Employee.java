package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_code")
    private Integer employeeCode;

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String surname1;
    @Column(length = 50)
    private String surname2;
    @Column(length = 10, nullable = false)
    private String extenxion;
    @Column(length = 100, nullable = false)
    private String email;
    @Column( name = "office_id", length = 10, nullable = false)
    private String officeId;

    @Column(length = 50 )
    private Integer managerCode;
    @Column(length = 50)
    private String position;

    public Employee() {
    }

    public Employee(String name, String surname1, String surname2, String extenxion, String email, String officeCod, Integer managerCode, String position) {
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.extenxion = extenxion;
        this.email = email;
        this.officeId = officeCod;
        this.managerCode = managerCode;
        this.position = position;
    }

    public Employee(String name, String surname1, String extenxion, String email, String officeCod) {
        this.name = name;
        this.surname1 = surname1;
        this.extenxion = extenxion;
        this.email = email;
        this.officeId = officeCod;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCod) {
        this.employeeCode = employeeCod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname1() {
        return surname1;
    }

    public void setSurname1(String surname1) {
        this.surname1 = surname1;
    }

    public String getSurname2() {
        return surname2;
    }

    public void setSurname2(String surname2) {
        this.surname2 = surname2;
    }

    public String getExtenxion() {
        return extenxion;
    }

    public void setExtenxion(String extenxion) {
        this.extenxion = extenxion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeCod) {
        this.officeId = officeCod;
    }

    public Integer getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(Integer managerCode) {
        this.managerCode = managerCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeCode=" + employeeCode +
                ", name='" + name + '\'' +
                ", surname1='" + surname1 + '\'' +
                ", surname2='" + surname2 + '\'' +
                ", extenxion='" + extenxion + '\'' +
                ", email='" + email + '\'' +
                ", officeId='" + officeId + '\'' +
                ", managerCode=" + managerCode +
                ", position='" + position + '\'' +
                '}';
    }
}
