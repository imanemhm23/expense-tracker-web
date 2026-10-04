package com.example.expensetrackerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerWebApplication {

	public static void main(String[] args) {
    Database.createTable();
    SpringApplication.run(ExpenseTrackerWebApplication.class, args);
}
}
