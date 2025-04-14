package com.example.demo.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "composition_recipe")
@IdClass(CompositionPrimaryKey.class)  // Указываем класс составного ключа
//@IdClass(CompositionRecipeId.class)  // Класс для составного ключа
public class CompositionRecipe {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;  // Сущность Recipe, связывающаяся с таблицей recipes

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;  // Сущность Ingredient, связывающаяся с таблицей ingredients

    @Column(name = "quantity")
    private double quantity;  // Количество ингредиента
    // Конструктор по умолчанию (обязателен для JPA)
    public CompositionRecipe() {
    }

    // Конструктор с параметрами
    public CompositionRecipe(Recipe recipe, Ingredient ingredient, double quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
    // Геттеры и сеттеры
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }



    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}

