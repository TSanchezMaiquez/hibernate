package com.example.model.example2;

import jakarta.persistence.*;

@Entity
public class Company {
    @EmbeddedId
    @AttributeOverride(name = "cif", column = @Column(name = "company_cif"))
    @AttributeOverride(name = "brand", column = @Column(name = "company_brand"))
    private CompanyPK id;

    private String location;
    private Integer EmployeeNumber;

    public Company() {
    }

    public Company(CompanyPK id, String location, Integer employeeNumber) {
        this.id = id;
        this.location = location;
        EmployeeNumber = employeeNumber;
    }

    public CompanyPK getId() {
        return id;
    }

    public void setId(CompanyPK id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployeeNumber() {
        return EmployeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        EmployeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", EmployeeNumber=" + EmployeeNumber +
                '}';
    }
}
