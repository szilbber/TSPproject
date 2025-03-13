-- Вставка тестовых данных в таблицу users
INSERT INTO users (id_user, name, password, bday, phone, mail)
VALUES
(1, 'John Doe', 'password123', '1990-01-15', '123-456-7890', 'john.doe@example.com'),
(2, 'Jane Smith', 'password456', '1992-03-25', '098-765-4321', 'jane.smith@example.com'),
(3, 'Alice Johnson', 'password789', '1985-07-20', '555-123-4567', 'alice.johnson@example.com');

-- Вставка тестовых данных в таблицу categories
INSERT INTO categories (id_category, title)
VALUES
(1, 'Dessert'),
(2, 'Main Course'),
(3, 'Appetizer');

-- Вставка тестовых данных в таблицу recipes
INSERT INTO recipes (id_recipe, id_user, title, id_category, description, manual, time, picture)
VALUES
(1, 1, 'Chocolate Cake', 1, 'A delicious chocolate cake', '1. Preheat the oven...', '1 hour', NULL),
(2, 2, 'Spaghetti Carbonara', 2, 'A classic Italian pasta dish', '1. Boil the pasta...', '30 minutes', NULL),
(3, 3, 'Caesar Salad', 3, 'A fresh salad with Caesar dressing', '1. Chop the lettuce...', '15 minutes', NULL);

-- Вставка тестовых данных в таблицу ingredients
INSERT INTO ingredients (id_ingredient, title_ingredient, unit_measure)
VALUES
(1, 'Flour', 'grams'),
(2, 'Eggs', 'pieces'),
(3, 'Butter', 'grams'),
(4, 'Spaghetti', 'grams'),
(5, 'Parmesan', 'grams'),
(6, 'Lettuce', 'grams'),
(7, 'Caesar Dressing', 'ml');

-- Вставка тестовых данных в таблицу favorite_recipes
INSERT INTO favorite_recipes (id_user, id_recipe)
VALUES
(1, 1),
(2, 2),
(3, 3);

-- Вставка тестовых данных в таблицу composition_recipe
INSERT INTO composition_recipe (id_recipe, id_ingredient, quantity)
VALUES
(1, 1, 250.00), -- Chocolate Cake uses 250g of Flour
(1, 2, 2.00),   -- Chocolate Cake uses 2 Eggs
(1, 3, 100.00), -- Chocolate Cake uses 100g of Butter
(2, 4, 200.00), -- Spaghetti Carbonara uses 200g of Spaghetti
(2, 5, 50.00),  -- Spaghetti Carbonara uses 50g of Parmesan
(3, 6, 100.00), -- Caesar Salad uses 100g of Lettuce
(3, 7, 30.00);  -- Caesar Salad uses 30ml of Caesar Dressing

-- Вставка тестовых данных в таблицу shopping_list
INSERT INTO shopping_list (id_user, id_ingredient, quantity, status)
VALUES
(1, 1, 500.00, TRUE), -- John wants to buy 500g of Flour
(1, 2, 12.00, FALSE), -- John already bought 12 Eggs
(2, 4, 300.00, TRUE), -- Jane wants to buy 300g of Spaghetti
(3, 6, 150.00, TRUE); -- Alice wants to buy 150g of Lettuce