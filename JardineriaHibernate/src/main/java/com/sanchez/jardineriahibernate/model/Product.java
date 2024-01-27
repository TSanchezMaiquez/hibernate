package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @Column(name = "product_code", length = 15)
    private String productCode;
    @Column(length = 70, nullable = false)
    private String name;
    @Column(name = "product_range_id", length = 50, nullable = false)
    private String rangeId;
    @Column(length = 25)
    private String dimensions;
    @Column(length = 50)
    private String supplier;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private int stock;
    @Column(name = "sales_price", precision = 15, scale = 2, nullable = false)
    private BigDecimal salesPrice;
    @Column(name = "supplier_price", precision = 15, scale = 2)
    private BigDecimal supplierPrice;

    public Product() {
    }

    public Product(String id, String name, String rangeId, String dimensions, String supplier, String description, int stock, BigDecimal salesPrice, BigDecimal supplierPrice) {
        this.productCode=id;
        this.name = name;
        this.rangeId = rangeId;
        this.dimensions = dimensions;
        this.supplier = supplier;
        this.description = description;
        this.stock = stock;
        this.salesPrice = salesPrice;
        this.supplierPrice = supplierPrice;
    }

    public Product(String id, String name, String rangeId, int stock, BigDecimal salesPrice) {
        this.productCode = id;
        this.name = name;
        this.rangeId = rangeId;
        this.stock = stock;
        this.salesPrice = salesPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRangeId() {
        return rangeId;
    }

    public void setRangeId(String range) {
        this.rangeId = range;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(BigDecimal supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", name='" + name + '\'' +
                ", rangeId='" + rangeId + '\'' +
                ", dimensions='" + dimensions + '\'' +
                ", supplier='" + supplier + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", salesPrice=" + salesPrice +
                ", supplierPrice=" + supplierPrice +
                '}';
    }
}
