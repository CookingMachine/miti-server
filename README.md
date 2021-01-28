# miti-server
https://orizonjust.docs.apiary.io/#
Сервер для проекта MITI
# Database diagram
![alt text](https://github.com/CookingMachine/miti-server/blob/master/DBdiagram.jpg)
Ссылка на диаграмму - https://drive.google.com/file/d/1aaTl1KvdV7zagDUnrrh5HFgmzWXdVkRd/view?usp=sharing
# Список запросов
## Авторизация

|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/auth`|Body: {<br>"username": "admin",<br> "password": "qwerty"<br>}|{"jwtToken": String<br>}|Каждый запрос на сервер сопровождается с header:authorization : "Bearer jwtToken"|

## Запросы к таблице User
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addUser`|Body: {<br>"username": "login",<br>"password": "password",<br>"email": "email@miti.ar",<br>"role": Long<br>}| {<br>"id": 4,<br>"username": String,<br>"email": String,<br>"status": boolean,<br>"role": Role|Integer<br>}|role:<br>0.USER<br>1.MODERATOR<br>2.ADMIN|
|GET `/getUserById`|Param: link.com/<br>getUserById?UserId=0|{<br>"id": Long,<br>"username": String,<br>"email": "String,<br>"status": boolean,<br>"role": Role|Integer<br>}|userId:1-infinity|
|GET `/getUserByUsername`|Param: link.com/<br>getUserByUsername?username=admin|{<br>"id": 1,<br>"username": "admin",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUserByEmail`|Param: link.com/<br>getUserByEmail?email=qwerty@gmail.com|{<br>"id": 1,<br>"username": "admin",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsers`|none|{<br>"id": 1,<br>"username": "admin",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUsersByStatus`|Param: link.com/<br>getUsersByStatus?status=true|{<br>"id": 1,<br>"username": "admin",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|Status:<br>true - active<br>false - inactive|
|GET `/getUsersByRole`|Param: link.com/<br>getUsersByRole?roleName=USER|{<br>"id": 1,<br>"username": "admin",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|Rolename: <br>USER<br>MODERATION<br>ADMINISTRATION|
|DELETE `/deleteUserById`|Param: link.com/<br>deleteUserById?userId=1|"Done!" if user has been deleted!||
## Запросы к таблице Recipe
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addRecipe`|Body: {<br>"name": "Стейк с кровью",<br>"description": "Жарь",<br>"author": {<br>"id": 1<br>},<br>"category": {<br>"id": "meat"<br>}<br>}|{<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "meat",<br>"name": "Мясо"<br>},<br>}|В body запроса можно указать только id для author и category|
|GET `/getRecipeById`|Param: link.com/<br>getRecipeById?recipeId=1|{<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь и жри",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "seconds",<br>"name": "Вторые блюдо"<br>}<br>}||
|GET `/getRecipeByName`|Param: link.com/<br>getRecipeByName?name=Борщ|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getAllRecipes`|none|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getRecipesByAuthorId`|Param: link.com/<br>getRecipesByAuthorId?authorId=3|{<br>"id": 3,<br>"name": "Борщ",<br>"description": "Еще",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}||
|GET `/getRecipesByCategoryId`|Param: link.com/<br>getRecipesByCategoryId?categoryId=salads|{<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "Кл",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты",<br>}<br>}|categoryId является строчным полем|
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
## Зпросы к таблице ContextIngredient
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addContextIngredient`|Body: {<br>"amount": 3,<br>"measure": 2,<br>"ingredient": {<br>"id": "milk"<br>},<br>"recipe": {<br>"id": 3<br>}<br>}|{<br>"id": 9,<br>"amount": 3,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "milk",<br>"name": "молоко",<br>"category": "MILK_PRODUCTS"<br>},<br>"recipe": {<br>"id": 3,<br>"name": "Борщ",<br>"description": "Е",<br>"author": {<br>"id": 3,<br>"username": "user",<br>"email": "user@gmail.com",<br>"status": true,<br>"role": "USER"<br>},<br>"category": {<br>"id": "firsts",<br>"name": "Первые блюдо"<br>}<br>}<br>}|В body запроса можно указать только id для ingredient и recipe|
|GET `/getContextIngredientById`|Param: link.com/<br>getContextIngredientById<br>?contextIngredientId=4|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}||
|GET `/getAllContextIngredients`|Param: link.com/<br>getAllContextIngredients|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}||
|GET `/getContextIngredientsByAmountLessThan`|Param: link.com/<br>getContextIngredients<br>ByAmountLessThan?amount=10|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}|Возвращаются все объекты, у которых amount < param|
|GET `/getContextIngredientsByAmountGreaterThan`|Param: link.com/<br>getContextIngredients<br>ByAmountGreaterThan?amount=0|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}|Возвращаются все объекты, у которых amount > param|
|GET `/getContextIngredientsByMeasure`|Param: link.com/<br>/getContextIngredientsBy<br>Measure?measureName=Kg|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}|measureName указывать только типом String|
|GET `/getContextIngredientsByIngredientId`|Param: link.com/<br>/getContextIngredients<br>ByIngredientId?ingredientId<br>=cucumber|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}|ingredientId является типом String|
|GET `/getContextIngredientsByRecipeId`|Param: link.com/<br>/getContextIngredientsByRecipe<br>Id?recipeId=2|{<br>"id": 4,<br>"amount": 1,<br>"measure": "Kg",<br>"ingredient": {<br>"id": "cucumber",<br>"name": "Огурец свежий",<br>"category": "VEGETABLES"<br>},<br>"recipe": {<br>"id": 2,<br>"name": "French Fries",<br>"description": "П",<br>"author": {<br>"id": 2,<br>"username": "moder",<br>"email": "moder@gmail.com",<br>"status": true,<br>"role": "MODERATION"<br>},<br>"category": {<br>"id": "snacks",<br>"name": "Закуски"<br>}<br>}<br>}||
|DELETE `/deleteContextIngredientById`|Param: link.com/<br>/deleteContextIngredientById<br>?ContextIngredientId=4|"Done!" if contextIngredient has been deleted||
## Запросы к таблице Comment
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addComment`|Body: {<br>"comment": "Привет всем!",<br>"commentator": {<br>"id": 1<br>},<br>"recipe": {<br>"id": 1<br>}<br>}|{<br>"id": 4,<br>"comment": "Привет всем!",<br>"commentator": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"recipe": {<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "К",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты"<br>}<br>}<br>}|В body запроса можно указать только id для commentator и recipe|
|GET `/getCommentById`|Param: link.com/<br>ommentById?commentId=4|{<br>"id": 4,<br>"comment": "Привет всем!",<br>"commentator": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"recipe": {<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "К",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты"<br>}<br>}<br>}||
|GET `/getCommentsByUserId`|Param: link.com/<br>getCommentsByUserId?userId=1|{<br>"id": 4,<br>"comment": "Привет всем!",<br>"commentator": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"recipe": {<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "К",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты"<br>}<br>}<br>}||
|GET `/getCommentsByRecipeId`|Param: link.com/<br>getCommentsByRecipeId?recipeId=1|{<br>"id": 4,<br>"comment": "Привет всем!",<br>"commentator": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"recipe": {<br>"id": 1,<br>"name": "Салат оливье",<br>"description": "К",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "salads",<br>"name": "Салаты"<br>}<br>}<br>}||
|DELETE `/deleteCommentById`|Param: link.com/<br>deleteCommentById?commentId=4|"Done!" if comment has been deleted||
## Запросы к таблице Category
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addCategory`|Body: {<br>"id": "test",<br>"name": "тест"<br>}|{<br>"id": "test",<br>"name": "тест"<br>}|id указывать в виде строки|
|GET `/getCategoryById`|Param: link.com/<br>getCategoryById?categoryId=test|{<br>"id": "test",<br>"name": "тест"<br>}||
|GET `/getCategoryByName`|Param: link.com/<br>getCategoryByName?name=тест|{<br>"id": "test",<br>"name": "тест"<br>}||
|DELETE `/deleteCategoryById`|Param: link.com/<br>deleteCategoryById?categoryId=test|"Done!" if category has been deleted|
