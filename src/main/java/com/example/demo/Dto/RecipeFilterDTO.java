package com.example.demo.Dto;

import java.util.List;

public class RecipeFilterDTO {

        private int id;
        private String title;
        private String time;

        // конструктор
        public RecipeFilterDTO
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
