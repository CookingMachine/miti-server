# miti-server
Сервер для проекта MITI
# Database diagram
![alt text](https://github.com/CookingMachine/miti-server/blob/master/DBdiagram.jpg)
Ссылка на диаграмму - https://drive.google.com/file/d/1aaTl1KvdV7zagDUnrrh5HFgmzWXdVkRd/view?usp=sharing
# Список запросов
## Авторизация

|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/auth`|Body: {<br>"username": "admin",<br> "password": "qwerty"<br>}|{"jwtToken": "eyJhbGciOiJIUzUxMiJ9.<br>eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2M<br>DI1MTMwNDcsImlhdCI6MTYwMjQ5NTA0N30.<br>nYSRFlOVtOV8DsLTsz2D-37SYnjo9MCQdMdxr2uyVKEkXXw7<br>7rXv-hxDaFv59aaMCd3il9aTfDhGVj5G5oB9UA"<br>}|Каждый запрос на сервер сопровождается с header:authorization : "Bearer jwtToken"|

## Запросы к таблице User
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addUser`|Body: {<br>"username": "login",<br>"password": "password",<br>"email": "email@miti.ar",<br>"role": 0<br>}| {<br>"id": 4,<br>"username": "admin2",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|role:<br>0.USER<br>1.MODERATOR<br>2.ADMIN|
|GET `/getUserById`|Param: link.com/<br>getUserById?UserId=0|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|userId:1-infinity|
|GET `/getUserByUsername`|Param: link.com/<br>getUserByUsername?username=admin|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUserByEmail`|Param: link.com/<br>getUserByEmail?email=qwerty@gmail.com|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsers`|none|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUsersByStatus`|Param: link.com/<br>getUsersByStatus?status=true|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|Status:<br>true - active<br>false - inactive|
|GET `/getUsersByRole`|Param: link.com/<br>getUsersByRole?roleName=USER|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|Rolename: <br>USER<br>MODERATION<br>ADMINISTRATION|
|DELETE `/deleteUserById`|Param: link.com/<br>deleteUserById?userId=1|"Done!" if user has been deleted!||
## Запросы к таблице Recipe
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addRecipe`|Body: {<br>"name": "Стейк с кровью",<br>"description": "Жарь",<br>"author": {<br>"id": 1<br>},<br>"category": {<br>"id": "meat"<br>}<br>}|{<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "qwerty",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "meat",<br>"name": "Мясо"<br>},<br>}|В body запроса указываем только id для author и category|
|GET `/getRecipeById`|Param: link.com/<br>getRecipeById?recipeId=1|{<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь и жри",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "qwerty",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "seconds",<br>"name": "Вторые блюдо"<br>}<br>}||
|GET `/getRecipeByName`|Param: link.com/<br>getRecipeByName?name=Борщ|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"password": "$2y$12$hri...",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getAllRecipes`|none|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"password": "$2y$12$hri...",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getRecipesByAuthorId`|Param: link.com/<br>getRecipesByAuthorId?authorId=3|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"password": "$2y$12$hri...",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getRecipesByCategoryId`|Param: link.com/<br>getRecipesByCategoryId?categoryId=salads|{<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "Кл",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "$2y$1...",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты",<br>}<br>}|categoryId является строчным полем|
|DELETE `/deleteRecipeById`|Param: link.com/<br>deleteRecipeById?recipeId=1|"Done!" if recipe has been deleted||
## Запросы к таблице Ingredient
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addIngredient`|Body: {<br>"id": "shrimp",<br>"name": "Креветка",<br>"category": "SEA_PRODUCTS"<br>}|{<br>"id": "shrimp",<br>"name": "Креветка",<br>"category": "SEA_PRODUCTS"<br>}|id имеет тип String<br>категорию ингридиента можно указывать в числовой форме, либо в формате enum|
|GET `/getIngredientById`|Param: link.com/<br>getIngredientById?ingredientId=tomato|{<br>"id": "tomato",<br>"name": "Помидор",<br>"category": "VEGETABLES"<br>}|Не забудь, что id - String|
|GET `/getIngredientByname`|Param: link.com/<br>getIngredientByName?name=Креветка|{<br>"id": "shrimp",<br>"name": "Креветка",<br>"category": "SEA_PRODUCTS"<br>}||
|GET `/getAllIngredients`|none|{<br>,"id": "tomato",<br>"name": "Помидор",<br>"category": "VEGETABLES"<br>}||
|GET `/getIngredientsByCategory`|Param: link.com/<br>getIngredientsByCategory?categoryName=SEA_PRODUCTS|{<br>"id": "mussels",<br>"name": "мидии",<br>"category": "SEA_PRODUCTS"<br>}|categoryName указывается только строкой|
|DELETE `/deleteIngredientById`|Param: link.com/<br>deleteIngredientById?ingredientId=ice|"Done!" if ingredients has been deleted||
