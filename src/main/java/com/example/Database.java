package com.example.expensetrackerweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    static final String URL = "jdbc:sqlite:expenses.db";

    static void createTable() {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS expenses ("
                     + "id INTEGER PRIMARY KEY, name TEXT, amount REAL)")) {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void addExpense(String name, double amount) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO expenses (name, amount) VALUES (?, ?)")) {
            ps.setString(1, name);
            ps.setDouble(2, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM expenses");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Expense(rs.getInt("id"), rs.getString("name"), rs.getDouble("amount")));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }

    static void deleteExpense(int id) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM expenses WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
