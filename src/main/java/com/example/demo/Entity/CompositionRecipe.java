package com.example.demo.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "composition_recipe")
@IdClass(CompositionPrimaryKey.class)  // Указываем класс составного ключа
public class CompositionRecipe {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe", insertable = false, updatable = false)
    private Recipe recipe;  // Сущность Recipe, связывающаяся с таблицей recipes

    @Id
    @ManyToOne
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id_ingredient", insertable = false, updatable = false)
    private Ingredient ingredient;  // Сущность Ingredient, связывающаяся с таблицей ingredients

    @Column(name = "quantity")
    private double quantity;  // Количество ингредиента

    // Геттеры и сеттеры
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