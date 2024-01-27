package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "payment_method", nullable = false, length = 40)
    private int paymentMethod;
    @Column(name = "transaction_code", nullable = false, length = 50)
    private int transactionCode;
    @Column(name = "payment_date", nullable = false)
    private Date paymentDate;
    @Column(name = "total_amount",precision = 15, scale = 2, nullable = false)
    private BigDecimal totalAmount;

    public PaymentMethod() {
    }

    public PaymentMethod(int paymentMethod, int transactionCode, Date paymentDate, BigDecimal totalAmount) {
        this.paymentMethod = paymentMethod;
        this.transactionCode = transactionCode;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(int transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "customerId=" + customerId +
                ", paymentMethod=" + paymentMethod +
                ", transactionCode=" + transactionCode +
                ", paymentDate=" + paymentDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
