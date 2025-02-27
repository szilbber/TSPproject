**Группа:** 6303-010302D

**Авторы:** Диш Софья и Шкуркина Мария

# Приложение: книга рецептов

**Функционал**
- Регистрация и авторизация пользователей.
- Создание новых рецептов.
- Добавление рецептов в избранное.
- Просмотр, редактирование и удаление уже существующих рецептов.
- Поиск рецептов по названию, категории и ингредиентам.
- Создание списока продуктов.


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
- Ingredients(id_ingridient, title): Хранит информацию об ингридиентах.
- Composition_recept(id_composition, id_recipes, id_ingridient, quantity, unit): Хранит информацию о том, какие ингридиенты входят в состав рецепта.
- Categories(id_category, title): Хранит информацию о категориях.
- Favorite_recipes(id_user, id_recipe): Хранит информацию о том, какие рецепты добавленны в избранное.
- Shopping_list(id_user, id_ingridient, quantity, unit, status): Хранит инфорацию об ингридиентах, которые пользователь хочет купить.


**Структура REST API**

1.     Регистрация пользователя
Метод: POST
URL: /users
Параметры запроса: {
	"username": "name",
	"email": "name@example.com",
	“phone”: “89171119923”
		“bday”: “11.22.3333”
	"password": "pass"
}
Ответ: {
    "status": "success",
    "user_id": 1,
                   “token”:”/…”
    "message": "User registered successfully"
}

2.     Вход в систему
Метод: POST
URL: /users/login
Параметры запроса: {
	"username": "name",
	"password": "pass"
}
 Ответ: {
"status": "success",
 "user_id": 1
 }
 
3. Создание рецепта (POST)
Метод: POST
URL: /recipes
Параметры запроса: {
  "title": "Паста с томатным соусом",
  “categories”: “Второе”, 
“description”: “Томатная паста пришла к нам из Италии….”
  "ingredients": [
{ ‘’title”: помидоры
“quantity”: 200
“unit”: грамм},
{ ‘’title”: сливки
“quantity”: 500
“unit”: мл}
  ],
  "manual": "Отварите пасту. Обжарьте лук и чеснок на оливковом масле, добавьте помидоры и тушите. Смешайте с пастой и подавайте."
“time”: “30 мин”
“picture”
}
Ответ:
{
   "status": "success",
    "id_recipe": 1,
    "message": "Recipe added successfully"
}

4. Получение списка рецептов (GET)
Метод: GET
URL: /recipes
Параметры запроса: {
  “categories”: “Второе”, 
}
Ответ:
[
  {
    "id": 1,
    "title": "Паста с томатным соусом"
  },
  {
    "id": 2,
    "title": "Салат Цезарь"
  }
]

5. Получение конкретного рецепта 
Метод: GET
URL: /recipes/{recipe_id}
Ответ:
{
  "id_recipe": 1,
  "title": "Паста с томатным соусом",
“category”: “суп”,
“description”: …,
  "ingredients": [...],
  "manual": "...",
  "time":...
“picture”:...
}

6. Обновление рецепта 
Метод: PUT
URL: /recipes/{recipe_id}
Параметры запроса: {
  "title": "Паста с томатным соусом",
  “categories”: “Второе”, 
“description”: “Томатная паста пришла к нам из Италии….”
  "ingredients": [
{ ‘’title”: помидоры
“quantity”: 200
“unit”: грамм},
{ ‘’title”: сливки
“quantity”: 500
“unit”: мл}
  ],
  "manual": "...."
“time”: “30 мин”
“picture”
}
Ответ:
{
   "status": "success",
    "id_recipe": 1,
    "message": "Recipe updated  successfully"
}

7. Удаление рецепта 
Метод: DELETE
URL: /recipes/{recipe_id}
Ответ:
{
   "status": "success",
    "message": "Recipe deleted  successfully"
}

8. Добавление рецепта в избранное
Метод: POST
URL: /users/{user_id}/favorites
Ответ:
{
"message": "Favourite recipe added successfully."
}

9. Удаление рецепта из избранного
Метод: DELETE
URL: /users/{user_id}/favorites/{recipe_id}
Ответ:
{
   "status": "success",
    "message": "Favourite recipe deleted  successfully."
}

10. Добавление ингредиента в список покупок
 Метод: POST
URL: /users/{user_id}/grocery_list
Параметры запроса: {
“title”: “молоко”
}

Ответ:
{
   "status": "success",
    "message": "Ingredient added successfully."
}

11. Удаление ингредиента в список покупок
Метод: DELETE
URL: /users/{user_id}/grocery_list/{ingredient_id}
Ответ:
{
   "status": "success",
    "message": "Ingredient deleted successfully."
}





**Стэк технологий**

