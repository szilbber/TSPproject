package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    //Первичный ключ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingredient")
    private int id_ingredient;

    @Column(name = "title")
    private String title;

    @Column(name = "unit_measure")
    private String unit_measure;

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getUnitMeasure() {
        return unit_measure;
    }

    public void setUnitMeasure(String unit_measure) {
        this.unit_measure = unit_measure;
    }
}
