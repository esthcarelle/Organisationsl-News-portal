# Organisational News Portal
#### Organisationa News Portal, 28th September 2019
#### By **Cates NSENGIYUMVA**
## Description
Organisational News Portal should be to provide news/articles/posts that are available to all employees.
## Setup/Installation Requirements
* Go to GitHub account => www.github.com/ktscates
* Go to my repositories
* Select Organisational-Api.
* Click on the green button labeled: clone or download. you can clone or download it directly into your computer.
* Unzip the folder.
* You should have all the project files.
## BDD
In PSQL:
* CREATE DATABASE news;
* CREATE TABLE users (id serial PRIMARY KEY, username varchar, iddepartment int, role varchar, position varchar);
* CREATE TABLE departs (id serial PRIMARY KEY, nameofdepartment varchar, detail varchar, numberemployees int);
* CREATE TABLE news (id serial PRIMARY KEY, content varchar, iddepartment int, usernameid int)
## Known Bugs
There no bugs in the application.
Failed to deploy on heroku.
## Technologies Used
* Java
* Spark Java
## Support and contact details
If you run into errors, please feel free to contact me on github.
### License
Copyright (c) 2019 **Cates NSENGIYUMVA**