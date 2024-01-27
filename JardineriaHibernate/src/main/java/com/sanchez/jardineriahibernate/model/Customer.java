package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_code")
    private int customerCode;

    @Column(name = "customer_name", length = 50, nullable = false)
    private String customerName;
    @Column(name = "contact_name", length = 30)
    private String contactName;
    @Column(name = "surname_contact", length = 30)
    private String surnameContact;
    @Column(length = 15, name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(length = 15, nullable = false)
    private String fax;
    @Column(length = 50, name = "first_address", nullable = false)
    private String address1;
    @Column(length = 50, name = "second_address")
    private String address2;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(length = 50)
    private String region;
    @Column(length = 50)
    private String country;
    @Column(length = 10, name = "post_code")
    private String postCode;

    @Column(name = "employee_code")
    private Integer employeeCode;
    @Column(name = "credit_limit", precision = 15, scale = 2)
    private BigDecimal creditLimit;


    public Customer() {
    }

    public Customer(String customerName, String contactName, String surnameContact, String phoneNumber, String fax, String address1, String address2, String city, String region, String country, String postCode, int employeeCode, BigDecimal creditLimit) {
        this.customerName = customerName;
        this.contactName = contactName;
        this.surnameContact = surnameContact;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.region = region;
        this.country = country;
        this.postCode = postCode;
        this.employeeCode = employeeCode;
        this.creditLimit = creditLimit;
    }

    public Customer(String customerName, String phoneNumber, String fax, String address1, String city) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.fax = fax;
        this.address1 = address1;
        this.city = city;
    }

    public int getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(int customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getSurnameContact() {
        return surnameContact;
    }

    public void setSurnameContact(String surnameContact) {
        this.surnameContact = surnameContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Integer getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(Integer employeeCode) {
        this.employeeCode = employeeCode;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerCode=" + customerCode +
                ", customerName='" + customerName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", surnameContact='" + surnameContact + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fax='" + fax + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", postCode='" + postCode + '\'' +
                ", employeeCode=" + employeeCode +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
