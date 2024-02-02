package com.example.tableperClass;

import jakarta.persistence.Entity;

@Entity  //Hereda de Vehicle
public class Motorcycle extends Vehicle{

    private Boolean hasCopilot;

    public Motorcycle() {
    }

    public Motorcycle(Long id, String manufacturer, Owner owner, Boolean hasCopilot) {
        super(id, manufacturer, owner);
        this.hasCopilot = hasCopilot;
    }

    public Boolean getHasCopilot() {
        return hasCopilot;
    }

    public void setHasCopilot(Boolean hasCopilot) {
        this.hasCopilot = hasCopilot;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "hasCopilot=" + hasCopilot +
                ", id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
