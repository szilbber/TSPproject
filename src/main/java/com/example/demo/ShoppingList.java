package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {
    @Column(name = "quantity")
    private double quantity;

    @Column(name = "status")
    private boolean status;

    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
