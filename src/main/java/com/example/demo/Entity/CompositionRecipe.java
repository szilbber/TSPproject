package com.example.demo.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "composition_recipe")
//@IdClass(CompositionPrimaryKey.class)  // Указываем класс составного ключа
public class CompositionRecipe {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;  // Сущность Recipe, связывающаяся с таблицей recipes

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

