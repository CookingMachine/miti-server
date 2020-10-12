# miti-server
Сервер для проекта MITI
# Database diagram
![alt text](https://github.com/CookingMachine/miti-server/blob/master/DBdiagram.jpg)
Ссылка на диаграмму - https://drive.google.com/file/d/1aaTl1KvdV7zagDUnrrh5HFgmzWXdVkRd/view?usp=sharing
# Список запросов
## Авторизация
Каждый запрос на сервер сопровождается с header:authorization : "Bearer jwtToken".
|Request's name|Request param/body|Response format|Example|Description|
|---|---|---|---|---|
|POST `/auth`|Body: {<br>'username' : 'login',<br> 'password' : 'password'<br>}|Json: {<br>'jwtToken' : "your jwtToken"<br>}|{<br>"jwtToken": "eyJhbGciOiJIUzUxMiJ9.<br>eyJzdWIiOiJhZG1pbjIiLCJleHAiOjE2M<br>DI1MTMwNDcsImlhdCI6MTYwMjQ5NTA0N30.<br>nYSRFlOVtOV8DsLTsz2D-37SYnjo9MCQdMdxr2uyVKEkXXw7<br>7rXv-hxDaFv59aaMCd3il9aTfDhGVj5G5oB9UA"<br>}||
## Запросы к таблице User
|Request's name|Request param/body|Response format|Example|Description|
|---|---|---|---|---|
|POST `/addUser`|Body: {<br>'username' : 'login',<br> 'password' : 'password',<br>'email' : 'email@miti.ar',<br>'role' : 0<br>}|Json: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}| {<br>"id": 4,<br>"username": "admin2",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|role:<br>0.USER<br>1.MODERATOR<br>2.ADMIN|
|GET `/getUserById`|Param: link.com/<br>getUserById?UserId=0|Json: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|userId:1-infinity|
|GET `/getUserByUsername`|Param: link.com/<br>getUserByUsername?username=admin|Json: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getUserByEmail`|Param: link.com/<br>getUserByEmail?email=qwerty@gmail.com|Json: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsers`||Json USER[]: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}||
|GET `/getAllUsersByStatus`|Param: link.com/<br>getUsersByStatus?status=true|Json USER[]: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "ADMIN"<br>}|Status:<br>true - active<br>false - inactive|
|GET `/getAllUsersByRole`|Param: link.com/<br>getUsersByRole?roleName=USER|Json USER[]: {<br>'id' : "0"<br>'username' : 'login',<br>'password' : "encoded by BCrypt",<br>'email' : "Your email",<br>'status' : true,<br>'role' : 'USER'<br>}|{<br>"id": 1,<br>"username": "admin",<br>"password": "$2a$10$BoPu...",<br>"email": "qwerty@miti.ar",<br>"status": true,<br>"role": "USER"<br>}|Rolename: <br>USER<br>MODERATION<br>ADMINISTRATION|
|DELETE `/deleteUserById`|Param: link.com/<br>deleteUserById?userId=1|"Done!" if user was deleted!<br>NULL if bad request!|"Done!"||
