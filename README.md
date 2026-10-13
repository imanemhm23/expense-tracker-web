# Expense Tracker Web

A web app to track my expenses, built with Java and Spring Boot.
This is Stage 4 of my learning project. It is the web version of my console [Expense Tracker](https://github.com/imanemhm23/my-first-java).

**Live demo:** https://expense-tracker-web-9lb9.onrender.com

*The free hosting puts the app to sleep when nobody visits. The first load can take up to a minute. Data may reset when the app restarts.*

## Features

- Add an expense (name and amount)
- See all expenses in a list
- See the total
- Delete an expense
- Data is saved in a SQLite database

## Built with

- Java 21
- Spring Boot 4.1.1 (Spring Web)
- Thymeleaf (HTML templates)
- SQLite and JDBC

## How to run

1. Install Java 21 and VS Code with the Extension Pack for Java.
2. Open this folder in VS Code.
3. Run `ExpenseTrackerWebApplication.java`.
4. Open `http://localhost:8080` in your browser.

The database file `expenses.db` is created automatically on the first run.

## How it works

- `Expense.java` is one expense (name, amount, id).
- `Database.java` talks to SQLite (create, add, read, delete).
- `PageController.java` answers browser requests.
- `templates/index.html` is the page, filled in by Thymeleaf.

## Roadmap

- [x] Page with list, add and delete
- [x] Nicer design
- [x] Put it online
- [ ] User login

## Author

imane El mohmouh