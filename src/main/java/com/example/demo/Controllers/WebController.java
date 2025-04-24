package com.example.demo.Controllers;


import com.example.demo.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/registration")
    public String registrationForm(Model model) {

        return "register";  // отображаем страницу регистрации (registration.html)

}
    @RequestMapping("/login")
    public String loginForm(Model model) {

        return "login";  // отображаем страницу входа (login.html)

    }
    @RequestMapping("/main")
    public String showMainPage() {
        return "main"; // thymeleaf найдет main.html
    }
    @RequestMapping("/recipes")
    public String showRecipes() {
        return "recipes"; // thymeleaf найдет main.html
    }
    @RequestMapping("/favourite_recipes")
    public String showFavRecipes() {
        return "favourite_recipes"; // thymeleaf найдет main.html
    }
    @RequestMapping("/profile")
    public String showMyProfile() {
        return "profile"; // thymeleaf найдет main.html
    }
    // Веб-метод для отображения страницы с рецептами категории
    @RequestMapping("/recipes/category/{categoryId}")
    public String getCategoryPage(@PathVariable("categoryId") Long categoryId) {

        // Возвращаем имя HTML-шаблона (например, category-recipes.html)
        return "recipes_category";
    }

    // Веб-метод для отображения страницы рецепта
    @RequestMapping("/getRecipe/{Id}")
    public String getRecipePage(@PathVariable("Id") Long id) {

        // Возвращаем имя HTML-шаблона (например, category-recipes.html)
        return "one_recipe";
    }
}
