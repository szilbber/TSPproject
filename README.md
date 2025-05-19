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
| HTTP Method | Description                      | Endpoint                                      | Request Parameters                          | Response Example                              | Notes                                                                                             |
|-------------|----------------------------------|----------------------------------------------|--------------------------------------------|------------------------------------------------|---------------------------------------------------------------------------------------------------|
| POST        | Создание нового пользователя     | /api/users/create                            | {"username": "string", "password": "string", "email": "string"} | {"id": 1, "username": "string", "email": "string"} | Принимает данные пользователя в теле запроса.                                                    |
| GET         | Получение всех пользователей     | /api/users                                   | -                                          | [{"id": 1, "username": "string", "email": "string"}, {...}] | Возвращает список всех пользователей.                                                             |
| GET         | Получение пользователя по ID     | /api/users/{id}                             | -                                          | {"id": 1, "username": "string", "email": "string"} | Возвращает информацию о пользователе по его ID.                                                  |
| PUT         | Обновление данных пользователя    | /api/users/update/{id}                      | {"username": "string", "email": "string"} | {"id": 1, "username": "string", "email": "string"} | Принимает обновленные данные пользователя в теле запроса.                                         |
| DELETE      | Удаление пользователя             | /api/users/delete/{id}                      | -                                          | {"message": "User deleted successfully."}  | Удаляет пользователя по его ID.                                                                   |
| POST        | Добавление в избранное           | /api/users/addFavourite                     | {"recipeId": "integer"}                 | {"message": "Recipe added to favourites."}  | Добавляет рецепт в избранное для текущего пользователя.                                           |
| DELETE      | Удаление из избранного           | /api/users/removeFavourite/{recipeId}      | -                                          | {"message": "Recipe removed from favourites."} | Удаляет рецепт из избранного для текущего пользователя.                                           |
| POST        | Создание новой категории         | /api/categories/create                       | {"name": "string"}                       | {"id": 1, "name": "string"}                | Принимает данные категории в теле запроса.                                                        |
| GET         | Получение всех категорий         | /api/categories                              | -                                          | [{"id": 1, "name": "string"}, {...}]       | Возвращает список всех категорий.                                                                  |
| GET         | Получение категории по ID        | /api/categories/getCategory/{id}            | -                                          | {"id": 1, "name": "string"}                | Возвращает информацию о категории по ее ID.                                                       |
| POST        | Создание нового ингредиента      | /api/ingredients/create                      | {"name": "string", "quantity": "string"}| {"id": 1, "name": "string", "quantity": "string"}| Принимает данные ингредиента в теле запроса.                                                      |
                                                                                                                            


**Стек технологий**

- PostgreSQL
- Java + Spring 
- HTML + JavaScript

