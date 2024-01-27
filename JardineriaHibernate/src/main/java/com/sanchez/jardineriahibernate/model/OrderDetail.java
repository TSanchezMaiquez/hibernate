package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "product_id", length = 15, nullable = false)
    private String productId;
    @Column(nullable = false)
    private int amount;
    @Column(name = "unit_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false, name = "line_number")
    private int lineNumber;

    public OrderDetail() {
    }

    public OrderDetail(String productId, int amount, BigDecimal unitPrice, int lineNumber) {
        this.productId = productId;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.lineNumber = lineNumber;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order_id=" + order_id +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", unitPrice=" + unitPrice +
                ", lineNumber=" + lineNumber +
                '}';
    }
}
