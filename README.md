**Группа:** 6303-010302D

**Авторы:** Диш Софья и Шкуркина Мария

# Приложение: книга рецептов

**Функционал**
- Регистрация и вход пользователей.
- Создание новых рецептов, ингредиентов и категорий.
- Добавление рецептов в избранное.
- Просмотр уже существующих рецептов.
- Поиск рецептов по названию, категории и ингредиентам.


**Схема взаимодействия компонентов**
1.	Запрос от клиента: Клиент отправляет запрос на сервер (например, для получения рецепта).
2.	Обработка запроса сервером: Сервер принимает запрос, обрабатывает его и обращается к базе данных для извлечения необходимой информации.
3.	Ответ базы данных: База данных возвращает запрашиваемую информацию серверу.
4.	Ответ сервера клиенту: Сервер формирует ответ и отправляет его обратно клиенту (например, возвращает рецепты опредленной категории).


**Схема базы данных**

![image](https://github.com/user-attachments/assets/8876b76d-b948-4262-bd8d-7ba31a3d8b71)


**Сущности**
- User(id_user,name ,password, bday, phone, mail, user_role): Хранит информацию о пользователях.
- Recipe(id_recipe, id_user, title, id_category, description, manual, time, picture: Хранит информацию о рецептах.
- Ingredient(id_ingridient, title): Хранит информацию об ингредиентах.
- Composition_recept(id_composition, id_recipes, id_ingridient, quantity, unit): Хранит информацию о том, какие ингредиенты входят в состав рецепта.
- Categoriy(id_category, title): Хранит информацию о категориях.
- Favorite_recipe(id_user, id_recipe): Хранит информацию о том, какие рецепты добавлены в избранное.
- Token(id, access_token, refresh_token, is_logged_out, user_id): Хранит информацию о токенах.


**Структура REST API**

| Метод | Название метода               | URL                               | Параметры запроса                                                                                                                                                                                                                           | Ответ                                                                                     |
|-------|-------------------------------|-----------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| POST  | Регистрация пользователя      | /a/registration                   | `{<br>  "username": "name",<br>  "email": "name@example.com",<br>  "phone": "89171119923",<br>  "bday": "11.22.3333",<br>  "password": "pass"<br>}`                                                                                       | `{<br>  "status": "success",<br>  "user_id": 1,<br>  "token": "/...",<br>  "message": "User registered successfully"<br>}` |
| POST  | Вход пользователя             | /a/login                          | `{<br>  "username": "name",<br>  "password": "pass"<br>}`                                                                                                                                                                              | `{<br>  "status": "success",<br>  "user_id": 1<br>}`                                     |
| POST  | Смена пароля                 | /a/password_change                | `{<br>  "username": "name",<br>  "old_password": "old_pass",<br>  "new_password": "new_pass"<br>}`                                                                                                                                   | `{<br>  "status": "success"<br>}`                                                       |
| POST  | Добавление рецепта           | /api/recipes/create               | `{<br>  "title": "Паста с томатным соусом",<br>  "categories": "Второе",<br>  "description": "Томатная паста пришла к нам из Италии….",<br>  "ingredients": [<br>    {<br>      "title": "помидоры",<br>      "quantity": 200,<br>      "unit": "грамм"<br>    },<br>    {<br>      "title": "сливки",<br>      "quantity": 500,<br>      "unit": "мл"<br>    }<br>  ],<br>  "manual": "Отварите пасту...",<br>  "time": "30 мин",<br>  "picture": "<...>"<br>}` | `{<br>  "status": "success",<br>  "id_recipe": 1,<br>  "message": "Recipe added successfully"<br>}` |
| GET   | Получение списка рецептов    | /api/recipes/search               | `{<br>  "categories": "Второе"<br>}`                                                                                                                                                                                                     | `[<br>  {<br>    "id": 1,<br>    "title": "Паста с томатным соусом"<br>  },<br>  {<br>    "id": 2,<br>    "title": "Салат Цезарь"<br>  }<br>]` |
| GET   | Получение выбранного рецепта  | /api/recipes/getRecipe/{id}      |                                                                                                                                                                                                                                              | `{<br>  "id_recipe": 1,<br>  "title": "Паста с томатным соусом",<br>  "category": "суп",<br>  "description": "...",<br>  "ingredients": [...],<br>  "manual": "...",<br>  "time": "...",<br>  "picture": "<...>"<br>}` |



**Стек технологий**

- PostgreSQL
- Java + Spring 
- HTML + JavaScript

