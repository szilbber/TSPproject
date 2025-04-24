package com.example.demo.Dto;

import java.util.List;

public class RecipeAnswerDTO {
    private int id;
    private int categoryId; // Используем ID категории
    private String title;
    private String description;
    private String manual;
    private String time;
    private List<IngredientDTO> ingredients;





        // конструктор
        public RecipeAnswerDTO
        (int id,int categoryId,String title, String description,String manual,String time)
        {
            this.id =id;
            this.categoryId=categoryId;
            this.title = title;
            this.description=description;
            this.manual=manual;
            this.time = time;

        }


public int getId() {
    return id;
}
    public int getCategoryId() {
        return categoryId;
    }

public String getTitle() {
    return title;
}
    public String getDescription() {
        return description;
    }

public String getManual() {
        return manual;
        }

public String getTime() {
    return time;
}

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
