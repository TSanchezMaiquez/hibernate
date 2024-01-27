package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "Customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_code")
    private int orderCode;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;
    @Column(name = "expected_order_date", nullable = false)
    private LocalDate expectedOrderDate;
    @Column(name = "delivery_order_date")
    private LocalDate deliveryOrderDate;
    @Column(length = 15, nullable = false)
    private String state;
    @Column(length = 1000)
    private String comments;
    @Column(name = "costumer_id", nullable = false)
    private int customerId;

    public CustomerOrder() {
    }

    public CustomerOrder(LocalDate orderDate, LocalDate expectedOrderDate, LocalDate deliveryOrderDate, String state, String comments, int customerId) {
        this.orderDate = orderDate;
        this.expectedOrderDate = expectedOrderDate;
        this.deliveryOrderDate = deliveryOrderDate;
        this.state = state;
        this.comments = comments;
        this.customerId = customerId;
    }

    public CustomerOrder(LocalDate orderDate, LocalDate expectedOrderDate, String state, int customerId) {
        this.orderDate = orderDate;
        this.expectedOrderDate = expectedOrderDate;
        this.state = state;
        this.customerId = customerId;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getExpectedOrderDate() {
        return expectedOrderDate;
    }

    public void setExpectedOrderDate(LocalDate expectedOrderDate) {
        this.expectedOrderDate = expectedOrderDate;
    }

    public LocalDate getDeliveryOrderDate() {
        return deliveryOrderDate;
    }

    public void setDeliveryOrderDate(LocalDate deliveryOrderDate) {
        this.deliveryOrderDate = deliveryOrderDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderCode=" + orderCode +
                ", orderDate=" + orderDate +
                ", expectedOrderDate=" + expectedOrderDate +
                ", deliveryOrderDate=" + deliveryOrderDate +
                ", state='" + state + '\'' +
                ", comments='" + comments + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
