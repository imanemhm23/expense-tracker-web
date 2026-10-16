# Expense Tracker Web

A web app to track my expenses, built with Java and Spring Boot.
This is Stage 4 of my learning project. It is the web version of my console [Expense Tracker](https://github.com/imanemhm23/my-first-java).

**Live demo:** https://expense-tracker-web-9lb9.onrender.com

*The free hosting puts the app to sleep when nobody visits. The first load can take up to a minute. Data may reset when the app restarts, so you may need to register again. This is a learning project, so please do not use a real password on the demo.*

## Features

- Register an account and log in or out
- Each user only sees their own expenses
- Add an expense (name and amount)
- See your expenses in a list
- See the total
- Delete an expense
- Data is saved in a SQLite database

## Built with

- Java 21
- Spring Boot 4.1.1 (Spring Web)
- Spring Security (login, logout, access rules)
- BCrypt (passwords are stored as hashes, never as plain text)
- Thymeleaf (HTML templates)
- SQLite and JDBC

## How to run

1. Install Java 21 and VS Code with the Extension Pack for Java.
2. Open this folder in VS Code.
3. Run `ExpenseTrackerWebApplication.java`.
4. Open `http://localhost:8080/register` in your browser and create an account.

The database file `expenses.db` is created automatically on the first run.

## How it works

- `Expense.java` is one expense (name, amount, id).
- `AppUser.java` is one user (id, username, hashed password).
- `Database.java` talks to SQLite (create tables, add, read and delete expenses, create and find users).
- `SecurityConfig.java` sets up Spring Security: BCrypt hashing, looking up users in the database, and which pages need a login.
- `RegisterController.java` handles the register page.
- `LoginController.java` shows the login page.
- `PageController.java` answers browser requests for the main page, add and delete, for the logged-in user only.
- `templates/index.html` is the main page, `templates/register.html` is the register page and `templates/login.html` is the login page, all filled in by Thymeleaf and in the same design.

## Roadmap

- [x] Page with list, add and delete
- [x] Nicer design
- [x] Put it online
- [x] User login (register, login, logout, own expenses only)
- [x] Custom login page in the same design

## Author

imane El mohmouh