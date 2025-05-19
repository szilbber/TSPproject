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

![image](https://github.com/user-attachments/assets/78e4eae9-8cdd-4e9b-8d25-1701c48ae650)



**Сущности**
- User(id_user,name ,password, bday, phone, mail, user_role): Хранит информацию о пользователях.
- Recipe(id_recipe, id_user, title, id_category, description, manual, time, picture: Хранит информацию о рецептах.
- Ingredient(id_ingridient, title): Хранит информацию об ингредиентах.
- Composition_recept(id_composition, id_recipes, id_ingridient, quantity, unit): Хранит информацию о том, какие ингредиенты входят в состав рецепта.
- Categoriy(id_category, title): Хранит информацию о категориях.
- Favorite_recipe(id_user, id_recipe): Хранит информацию о том, какие рецепты добавлены в избранное.
- Token(id, access_token, refresh_token, is_logged_out, user_id): Хранит информацию о токенах.


**Структура REST API**
| Метод  | Название метода                      | URL                                     | Параметры запроса                                                                                                                                                                                                                           | Ответ                                                                                     |
|--------|--------------------------------------|-----------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| POST   | Регистрация пользователя             | /a/registration                         | `{ "username": "name", "email": "name@example.com", "phone": "89171119923", "bday": "11.22.3333", "password": "pass" }`                                                                                                           | `{ "status": "success", "user_id": 1, "token": "/...", "message": "User registered successfully" }` |
| POST   | Вход пользователя                    | /a/login                                | `{ "username": "name", "password": "pass" }`                                                                                                                                                                                            | `{ "status": "success", "user_id": 1 }`                                                 |
| POST   | Смена пароля                        | /a/password_change                      | `{ "username": "name", "old_password": "old_pass", "new_password": "new_pass" }`                                                                                                                                                      | `{ "status": "success" }`                                                               |
| POST   | Добавление рецепта                  | /api/recipes/create                     | `{ "title": "Паста с томатным соусом", "categories": "Второе", "description": "...", "ingredients": [{ "title": "помидоры", "quantity": 200, "unit": "грамм" }, { "title": "сливки", "quantity": 500, "unit": "мл" }], "manual": "...", "time": "...", "picture": "<...>" }` | `{ "status": "success", "id_recipe": 1, "message": "Recipe added successfully" }` |
| GET    | Получение списка рецептов           | /api/recipes/search                     | `{ "categories": "Второе" }`                                                                                                                                                                                                             | `[ { "id": 1, "title": "Паста с томатным соусом" }, { "id": 2, "title": "Салат Цезарь" } ]` |
| GET    | Получение выбранного рецепта        | /api/recipes/getRecipe/{id}            |                                                                                                                                                                                                                                              | `{ "id_recipe": 1, "title": "...", ... }`                                               |
| GET    | Получить все ингредиенты            | /api/ingredients                        |                                                                                                                                                                                                                                              | `[ { "id": 1, "title": "помидоры", "unit": "грамм" }, { "id": 2, "title": "сливки", "unit": "мл" } ]` |
| POST   | Создание ингредиента                | /api/ingredients/create                 | `{ "title": "новый ингредиент", "unit": "грамм" }`| `{ "status": "success", "id_ingredient": 1, "message": "Ingredient created successfully" }` |
| GET    | Вывод всех категорий                | /api/categories                         |                                                                                                                                                                                                                                              | `[ { "id": 1, "title": "Второе" }, { "id": 2, "title": "Салаты" } ]`                     |
| POST   | Создание категории                   | /api/categories/create                  | `{ "title": "Новая категория" }`                                                                                                                                                                                                         | `{ "status": "success", "id_category": 1, "message": "Category created successfully" }` |
| GET    | Получение одной категории            | /api/categories/getCategory/{id}        |                                                                                                                                                                                                                                              | `{ "id_category": 1, "title": "...", ... }`                                             |
| GET    | Получение профиля пользователя       | /api/users/profile                      |                                                                                                                                                                                                                                              | `{ "user_id": 1, "username": "...", ... }`                                             |
| GET    | Получение всех рецептов пользователя | /api/users/myRecipe                    |                                                                                                                                                                                                                                              | `[ { "id_recipe": 1, "title": "..."} ]`                                                |
| GET    | Получение любимых рецептов          | /api/users/myFavouriteRecipe           |                                                                                                                                                                                                                                              | `[ { "id_recipe": 1, "title": "..."} ]`                                                |
| POST   | Добавление рецепта в избранное      | /api/users/favourite-recipes/{recipeId}|                                                                                                                                                                                                                                              | `{ "status":"success", "message":"Recipe added to favourites" }`                        |
| DELETE | Удаление рецепта из избранного      | /api/users/removeFavourite/{recipeId}   |                                                                                                                                                                                                                                              | `{ "status":"success", "message":"Recipe removed from favourites" }`                     |
                                             
                                                                                                                            


**Стек технологий**

- PostgreSQL
- Java + Spring 
- HTML + JavaScript
- DevOps: Docker, Docker Compose

