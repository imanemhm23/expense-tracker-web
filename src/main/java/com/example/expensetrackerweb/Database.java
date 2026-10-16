package com.example.expensetrackerweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    static final String URL = "jdbc:sqlite:expenses.db";

    static void createTable() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS expenses ("
                    + "id INTEGER PRIMARY KEY, name TEXT, amount REAL, user_id INTEGER)");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY, username TEXT UNIQUE, password TEXT)");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void addExpense(String name, double amount, int userId) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO expenses (name, amount, user_id) VALUES (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setDouble(2, amount);
            ps.setInt(3, userId);
            ps.executeUpdate();

            

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static ArrayList<Expense> getExpenses(int userId) {
        ArrayList<Expense> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM expenses WHERE user_id = ?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Expense(rs.getInt("id"), rs.getString("name"), rs.getDouble("amount")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return list;
    }

    static void deleteExpense(int id, int userId) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM expenses WHERE id = ? AND user_id = ?")) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static boolean createUser(String username, String hashedPassword) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO users (username, password) VALUES (?, ?)")) {
            ps.setString(1, username);
            ps.setString(2, hashedPassword);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    static AppUser findUser(String username) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM users WHERE username = ?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new AppUser(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }
}