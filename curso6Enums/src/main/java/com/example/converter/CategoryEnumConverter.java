package com.example.converter;

import com.example.model.EmployeeCategory;
import jakarta.persistence.AttributeConverter;

public class CategoryEnumConverter implements AttributeConverter<EmployeeCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EmployeeCategory attribute) {
        int num =0;
        switch (attribute){
            case JUNIOR:
                num = 1;
                break;
            case SENIOR:
                num=2;
                break;
            case MANAGER:
                num=3;
                break;
            case C_LEVEL:
                num=4;
                break;
        }
        return num;
    }

    @Override
    public EmployeeCategory convertToEntityAttribute(Integer dbData) {
        EmployeeCategory employee = null;

        switch (dbData){
            case 1:
                employee = EmployeeCategory.JUNIOR;
                break;
            case 2:
                employee = EmployeeCategory.SENIOR;
                break;
            case 3:
                employee = EmployeeCategory.MANAGER;
                break;
            case 4:
                employee = EmployeeCategory.C_LEVEL;
                break;
            default: throw new IllegalArgumentException("Unknown value: "+dbData);
        }
        return employee;
    }
}
