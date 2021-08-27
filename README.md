# Spring Boot Restaurant Online Ordering App

## About

This is a demo project for practicing Spring + Thymeleaf. The idea was to build some basic web app to order food from sushi restaurant. 

It was made using **Spring Boot**, **Spring Security**, **Thymeleaf**, **Spring Data JPA**, **Java MailSender**.
Database is in memory **H2**.

There is a login and registration functionality included.

Users can shop for products. Each user has his own shopping cart (session functionality).

## How to run

Once the app starts, go to the web browser and visit `http://localhost:8080/orderOnline`.
If u want to edit products or add new Admin, go to the `http://localhost:8080/admin`.
Admin username: **admin1**
Admin password: **admin1**

### H2 Database web interface

Go to the web browser and visit `http://localhost:8080/console_h2`

In field **JDBC URL** put 
```
jdbc:h2:file:./restaurant
```
