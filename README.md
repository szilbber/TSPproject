**Группа:** 6303-010302D

**Авторы:** Диш Софья и Шкуркина Мария

# Приложение: книга рецептов

**Функционал**
- Регистрация и авторизация пользователей.
- Создание новых рецептов.
- Добавление рецептов в избранное.
- Просмотр, редактирование и удаление уже существующих рецептов.
- Поиск рецептов по названию, категории и ингредиентам.
- Создание списока покупок.


**Схема взаимодействия компонентов**
1.	Запрос от клиента: Клиент отправляет запрос на сервер (например, для получения рецепта).
2.	Обработка запроса сервером: Сервер принимает запрос, обрабатывает его и обращается к базе данных для извлечения необходимой информации.
3.	Ответ базы данных: База данных возвращает запрашиваемую информацию серверу.
4.	Ответ сервера клиенту: Сервер формирует ответ и отправляет его обратно клиенту (например, возвращает рецепты опредленной категории).


**Схема базы данных**
![image](https://github.com/user-attachments/assets/bdb3e0d6-a97d-4276-b262-de3bcb48666d)


**Сущности**
- User(id_user,name ,password, bday, phone, mail): Хранит информацию о пользователях.
- Recipe(id_recipe, id_user, title, id_category, description, manual, time, picture: Хранит информацию о рецептах.
- Ingredient(id_ingridient, title): Хранит информацию об ингредиентах.
- Composition_recept(id_composition, id_recipes, id_ingridient, quantity, unit): Хранит информацию о том, какие ингредиенты входят в состав рецепта.
- Categoriy(id_category, title): Хранит информацию о категориях.
- Favorite_recipe(id_user, id_recipe): Хранит информацию о том, какие рецепты добавлены в избранное.
- Shopping_list(id_user, id_ingridient, quantity, unit, status): Хранит информацию об ингредиентах, которые пользователь хочет купить.


**Структура REST API**
| Метод      | Название метода               | URL                           | Параметры запроса                                                                                                                                                                                                                           | Ответ                                                                                     |
|------------|-------------------------------|-------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| POST       | Регистрация пользователя      | /users                        | {<br>  "username": "name",<br>  "email": "name@example.com",<br>  "phone": "89171119923",<br>  "bday": "11.22.3333",<br>  "password": "pass"<br>}                                                                                       | {<br>  "status": "success",<br>  "user_id": 1,<br>  "token": "/...",<br>  "message": "User registered successfully"<br>} |
| POST       | Вход пользователя             | /users/login                  | {<br>  "username": "name",<br>  "password": "pass"<br>}                                                                                                                                                                                  | {<br>  "status": "success",<br>  "user_id": 1<br>}                                     |
| POST       | Добавление рецепта           | /recipes                      | {<br>  "title": "Паста с томатным соусом",<br>  "categories": "Второе",<br>  "description": "Томатная паста пришла к нам из Италии….",<br>  "ingredients": [<br>    {<br>      "title": "помидоры",<br>      "quantity": 200,<br>      "unit": "грамм"<br>    },<br>    {<br>      "title": "сливки",<br>      "quantity": 500,<br>      "unit": "мл"<br>    }<br>  ],<br>  "manual": "Отварите пасту...",<br>  "time": "30 мин",<br>  "picture": "<...>"<br>} | {<br>  "status": "success",<br>  "id_recipe": 1,<br>  "message": "Recipe added successfully"<br>} |
| GET        | Получение списка рецептов    | /recipes                      | {<br>  "categories": "Второе"<br>}                                                                                                                                                                                                         | [<br>  {<br>    "id": 1,<br>    "title": "Паста с томатным соусом"<br>  },<br>  {<br>    "id": 2,<br>    "title": "Салат Цезарь"<br>  }<br>] |
| GET        | Получение выбранного рецепта      | /recipes/{recipe_id}         |                                                                                                                                                                                                                                              | {<br>  "id_recipe": 1,<br>  "title": "Паста с томатным соусом",<br>  "category": "суп",<br>  "description": "...",<br>  "ingredients": [...],<br>  "manual": "...",<br>  "time": "...",<br>  "picture": "<...>"<br>} |
| PUT        | Обновление рецепта           | /recipes/{recipe_id}         | {<br>  "title": "Паста с томатным соусом",<br>  "categories": "Второе",<br>  "description": "Томатная паста пришла к нам из Италии….",<br>  "ingredients": [<br>    {<br>      "title": "помидоры",<br>      "quantity": 200,<br>      "unit": "грамм"<br>    },<br>    {<br>      "title": "сливки",<br>      "quantity": 500,<br>      "unit": "мл"<br>    }<br>  ],<br>  "manual": "...",<br>  "time": "30 мин",<br>  "picture": "<...>"<br>} | {<br>  "status": "success",<br>  "id_recipe": 1,<br>  "message": "Recipe updated successfully"<br>} |
| DELETE     | Удаление рецепта             | /recipes/{recipe_id}         |                                                                                                                                                                                                                                             | {<br>  "status": "success",<br>  "message": "Recipe deleted successfully"<br>}          |
| POST       | Добавление в избранное        | /users/{user_id}/favorites    |                                                                                                                                                                                                                                              | {<br>  "message": "Favourite recipe added successfully."<br>}                           |
| DELETE     | Удаление из избранного        | /users/{user_id}/favorites/{recipe_id} |                                                                                                                                                                                                                                              | {<br>  "status": "success",<br>  "message": "Favourite recipe deleted successfully."<br>} |
| POST       | Добавление в список покупок   | /users/{user_id}/grocery_list | {<br>  "title": "молоко"<br>}                                                                                                                                                                                                               | {<br>  "status": "success",<br>  "message": "Ingredient added successfully."<br>}     |
| DELETE     | Удаление из списка покупок    | /users/{user_id}/grocery_list/{ingredient_id} |                                                                                                                                                                                                                                              | {<br>  "status": "success",<br>  "message": "Ingredient deleted successfully."<br>}   |





**Стек технологий**

- PostgreSQL
- Java + Spring 
- Android Studio

