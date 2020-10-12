# miti-server
Сервер для проекта MITI
# Database diagram
![alt text](https://github.com/CookingMachine/miti-server/blob/master/DBdiagram.jpg)
Ссылка на диаграмму - https://drive.google.com/file/d/1aaTl1KvdV7zagDUnrrh5HFgmzWXdVkRd/view?usp=sharing
# Список запросов
## Авторизация

|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/auth`|Body: {<br>"username": "login",<br> "password": "password"<br>}|{"jwtToken": "eyJhbGciOiJIUzUxMiJ9.<br>eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2M<br>DI1MTMwNDcsImlhdCI6MTYwMjQ5NTA0N30.<br>nYSRFlOVtOV8DsLTsz2D-37SYnjo9MCQdMdxr2uyVKEkXXw7<br>7rXv-hxDaFv59aaMCd3il9aTfDhGVj5G5oB9UA"<br>}|Каждый запрос на сервер сопровождается с header:authorization : "Bearer jwtToken"|

## Запросы к таблице User
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addUser`|Body: {<br>"username": "login",<br>"password": "password",<br>"email": "email@miti.ar",<br>"role": 0<br>}| {<br>"id": 4,<br>"username": "admin2",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|role:<br>0.USER<br>1.MODERATOR<br>2.ADMIN|
|GET `/getUserById`|Param: link.com/<br>getUserById?UserId=0|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|userId:1-infinity|
|GET `/getUserByUsername`|Param: link.com/<br>getUserByUsername?username=admin|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUserByEmail`|Param: link.com/<br>getUserByEmail?email=qwerty@gmail.com|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsers`|none|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsersByStatus`|Param: link.com/<br>getUsersByStatus?status=true|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|Status:<br>true - active<br>false - inactive|
|GET `/getAllUsersByRole`|Param: link.com/<br>getUsersByRole?roleName=USER|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|Rolename: <br>USER<br>MODERATION<br>ADMINISTRATION|
|DELETE `/deleteUserById`|Param: link.com/<br>deleteUserById?userId=1|"Done!" if user was deleted!<br>NULL if bad request!||
## Запросы к таблице Recipe
|Request's name|Request param/body|Response format|Description|
|---|---|---|---|
|POST `/addRecipe`|Body: {<br>"name": 'recipeName',<br>"description": 'asdf',<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "qwerty",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": 2<br>},<br>"category": {<br>"id": "seconds",<br>"name: "Вторые блюдо"<br>}<br>}|JSON: {<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь и жри",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "qwerty",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "seconds",<br>"name": "Вторые блюдо"<br>},<br>"ingredientContextList": null<br>}|В body запроса необходимо указывать все поля author и category|
|GET `/getRecipeById`|Param: link.com/<br>getRecipeById?recipeId=1|JSON: {<br>"id": 4,<br>"name": "Стейк",<br>"description": "Жарь и жри",<br>"author": {<br>"id": 1,<br>"username": "admin",<br>"password": "qwerty",<br>"email": "admin@gmail.com",<br>"status": true,<br>"role": "ADMINISTRATION"<br>},<br>"category": {<br>"id": "seconds",<br>"name": "Вторые блюдо"<br>},<br>"ingredientContextList": null<br>}||
