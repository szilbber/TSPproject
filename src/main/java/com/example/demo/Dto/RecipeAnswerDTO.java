package com.example.demo.Dto;

public class RecipeAnswerDTO {

        private int id;
        private String title;
        private String time;

        // конструктор
        public RecipeAnswerDTO
        (int id,String title, String time)
        {
            this.id =id;
            this.title = title;
            this.time = time;

        }


public int getId() {
    return id;
}

public String getTitle() {
    return title;
}

public String getTime() {
    return time;
}
}
