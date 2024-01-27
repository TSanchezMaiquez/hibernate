package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

@Entity
public class Office {
    @Id
    @Column(name= " office_code", length = 10)
    private String officeCode;
    @Column(length = 30, nullable = false)
    private String city;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(name = "region", length = 50)
    private String region;
    @Column(name= "post_code", length = 10, nullable = false)
    private String postCode;

    @Column(length = 20, name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(length = 50, nullable = false)
    private String address1;

    @Column(length = 50)
    private String address2;

    public Office() {
    }

    public Office(String officeCod, String city, String country, String region, String postCode, String phoneNumber, String address1, String address2) {
        this.officeCode = officeCod;
        this.city = city;
        this.country = country;
        this.region = region;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
        this.address2 = address2;
    }

    public Office(String officeCod, String city, String country, String postCode, String phoneNumber, String address1) {
        this.officeCode = officeCod;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.phoneNumber = phoneNumber;
        this.address1 = address1;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCod) {
        this.officeCode = officeCod;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "Office{" +
                "officeCode='" + officeCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                '}';
    }
}
