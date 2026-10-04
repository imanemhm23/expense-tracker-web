package com.example.expensetrackerweb;

public class Expense {
    int id;
    String name;
    double amount;

    Expense(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    Expense(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}