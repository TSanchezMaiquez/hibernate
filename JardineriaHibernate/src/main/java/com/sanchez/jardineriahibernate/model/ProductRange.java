package com.sanchez.jardineriahibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductRange {

    @Id
    @Column(name = "range_code", length = 50)
    private String rangeCode;
    @Column(length = 1000)
    private String html_description;
    @Column(name = "text_description", length = 1000)
    private String textDescription;
    @Column(length = 256)
    private String image;

    public ProductRange() {
    }

    public ProductRange(String rangeCode) {
        this.rangeCode = rangeCode;
    }

    public ProductRange(String rangeCode, String html_description, String textDescription, String image) {
        this.rangeCode = rangeCode;
        this.html_description = html_description;
        this.textDescription = textDescription;
        this.image = image;
    }

    public String getRangeCode() {
        return rangeCode;
    }

    public void setRangeCode(String rangeCode) {
        this.rangeCode = rangeCode;
    }

    public String getHtml_description() {
        return html_description;
    }

    public void setHtml_description(String html_description) {
        this.html_description = html_description;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ProductRange{" +
                "rangeCode='" + rangeCode + '\'' +
                ", html_description='" + html_description + '\'' +
                ", textDescription='" + textDescription + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
